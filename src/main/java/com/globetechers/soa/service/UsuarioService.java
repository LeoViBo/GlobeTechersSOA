package com.globetechers.soa.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globetechers.soa.domain.model.UsuarioModel;
import com.globetechers.soa.domain.model.vo.EmailVO;
import com.globetechers.soa.domain.model.vo.SenhaVO;
import com.globetechers.soa.dto.AuthRequestDTO;
import com.globetechers.soa.dto.AuthResponseDTO;
import com.globetechers.soa.dto.UsuarioRequestDTO;
import com.globetechers.soa.dto.UsuarioResponseDTO;
import com.globetechers.soa.exception.EmailJaCadastradoException;
import com.globetechers.soa.exception.UsuarioNaoEncontradoException;
import com.globetechers.soa.repository.UsuarioRepository;
import com.globetechers.soa.security.jwt.JwtService;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager; 
    private final JwtService jwtService; 

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UsuarioModel buscarModelPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmailValue(email) 
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));
    }

    public AuthResponseDTO autenticar(AuthRequestDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha())
        );

        UsuarioModel usuario = (UsuarioModel) authentication.getPrincipal(); 

        String jwt = jwtService.generateToken(authentication); 

        return AuthResponseDTO.builder()
                .token(jwt)
                .idUsuario(usuario.getId())
                .login(usuario.getEmail().getValue()) 
                .roles(usuario.getRolesAsString())
                .build();
    }

    @Transactional
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) { 
        if (usuarioRepository.existsByEmailValue(dto.getEmail())) { 
            throw new EmailJaCadastradoException(dto.getEmail());
        }
        
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        
        UsuarioModel novoUsuario = UsuarioModel.builder()
                .nome(dto.getNome())
                .email(new EmailVO(dto.getEmail()))
                .senha(new SenhaVO(senhaCriptografada))
                .areaAtuacao(dto.getAreaAtuacao())
                .nivelCarreira(dto.getNivelCarreira())
                .dataCadastro(dto.getDataCadastro() != null ? dto.getDataCadastro() : LocalDate.now())
                .build();
        
        UsuarioModel usuarioSalvo = usuarioRepository.save(novoUsuario);
        return UsuarioResponseDTO.fromModel(usuarioSalvo);
    }
    
    public UsuarioResponseDTO buscarPorId(Long id) { 
        UsuarioModel usuario = buscarModelPorId(id); 
        return UsuarioResponseDTO.fromModel(usuario); 
    }

    public List<UsuarioResponseDTO> listarTodos() { 
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponseDTO::fromModel)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO dto) { 
        UsuarioModel usuarioExistente = buscarModelPorId(id);

        if (!usuarioExistente.getEmail().getValue().equals(dto.getEmail())) {
            if (usuarioRepository.existsByEmailValue(dto.getEmail())) {
                throw new EmailJaCadastradoException(dto.getEmail());
            }
            usuarioExistente.setEmail(new EmailVO(dto.getEmail()));
        }

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            String novaSenhaCriptografada = passwordEncoder.encode(dto.getSenha());
            usuarioExistente.setSenha(new SenhaVO(novaSenhaCriptografada));
        }

        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setAreaAtuacao(dto.getAreaAtuacao());
        usuarioExistente.setNivelCarreira(dto.getNivelCarreira());

        UsuarioModel usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return UsuarioResponseDTO.fromModel(usuarioAtualizado); 
    }
    
    @Transactional
    public void excluirUsuario(Long id) {
        UsuarioModel usuario = buscarModelPorId(id);
        usuarioRepository.delete(usuario);
    }
}