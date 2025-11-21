package com.globetechers.soa.service;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globetechers.soa.domain.enums.RoleEnum;
import com.globetechers.soa.domain.model.UsuarioModel;
import com.globetechers.soa.domain.model.vo.EmailVO;
import com.globetechers.soa.domain.model.vo.SenhaVO;
import com.globetechers.soa.dto.AuthResponseDTO;
import com.globetechers.soa.dto.LoginRequestDTO;
import com.globetechers.soa.dto.RegisterRequestDTO;
import com.globetechers.soa.dto.UsuarioRequestDTO;
import com.globetechers.soa.exception.EmailJaCadastradoException;
import com.globetechers.soa.repository.UsuarioRepository;
import com.globetechers.soa.security.jwt.JwtService;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UsuarioRepository usuarioRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public AuthResponseDTO registerUser(RegisterRequestDTO dto) {

        if (usuarioRepository.existsByEmailValue(dto.getEmail())) {
            throw new EmailJaCadastradoException(dto.getEmail());
        }

        String hashedPassword = passwordEncoder.encode(dto.getSenha());

        EmailVO emailVO = new EmailVO(dto.getEmail());
        SenhaVO senhaVO = new SenhaVO(hashedPassword);

        Set<RoleEnum> roles = new HashSet<>();
        roles.add(RoleEnum.ROLE_USER);
        if (dto.getEmail().endsWith("@globetechers.com")) {
            roles.add(RoleEnum.ROLE_ADMIN);
        }

        UsuarioModel novoUsuario = UsuarioModel.builder()
                .nome(dto.getNome())
                .email(emailVO)
                .senha(senhaVO)
                .areaAtuacao(dto.getAreaAtuacao())
                .nivelCarreira(dto.getNivelCarreira())
                .roles(roles)
                .build();

        UsuarioModel usuarioSalvo = usuarioRepository.save(novoUsuario);

        String token = jwtService.generateToken(usuarioSalvo);

        return AuthResponseDTO.builder()
                .token(token)
                .idUsuario(usuarioSalvo.getId())
                .login(usuarioSalvo.getEmail().getValue())
                .roles(new ArrayList<>(usuarioSalvo.getRolesAsString()))
                .mensagem("Registro realizado com sucesso!")
                .build();
    }

    public AuthResponseDTO authenticateUser(LoginRequestDTO dto) {
        UsuarioModel usuario = usuarioRepository.findByEmailValue(dto.getEmail())
                .orElseThrow(() -> {
                    System.out.println("====================");
                    System.out.println("Email não encontrado");
                    System.out.println("====================");
                    return new RuntimeException("Email não encontrado");
                });


        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha().getValue())) {
            System.out.println("===============");
            System.out.println("Senha incorreta");
            System.out.println("===============");
            throw new RuntimeException("Senha incorreta");
        }

        String token = jwtService.generateToken(usuario);

        return AuthResponseDTO.builder()
                .token(token)
                .idUsuario(usuario.getId())
                .login(usuario.getEmail().getValue())
                .roles(new ArrayList<>(usuario.getRolesAsString()))
                .mensagem("Login realizado com sucesso!")
                .build();
    }

}
