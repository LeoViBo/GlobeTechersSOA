package com.globetechers.soa.service;

import com.globetechers.soa.domain.enums.RoleEnum;
import com.globetechers.soa.domain.model.UsuarioModel;
import com.globetechers.soa.domain.model.vo.EmailVO;
import com.globetechers.soa.domain.model.vo.SenhaVO;
import com.globetechers.soa.dto.AuthResponseDTO;
import com.globetechers.soa.dto.LoginRequestDTO;
import com.globetechers.soa.dto.RegisterRequestDTO;
import com.globetechers.soa.exception.EmailJaCadastradoException; 
import com.globetechers.soa.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    
    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public AuthResponseDTO registerUser(RegisterRequestDTO dto) {
        if (usuarioRepository.existsByEmailValue(dto.getEmail())) {
            throw new EmailJaCadastradoException(dto.getEmail()); 
        }

        String hashedPassword = "FAKE_HASH_" + dto.getSenha(); 
        
        EmailVO emailVO = new EmailVO(dto.getEmail());
        SenhaVO senhaVO = new SenhaVO(hashedPassword); 

        Set<RoleEnum> assignedRoles = new HashSet<>();
        assignedRoles.add(RoleEnum.ROLE_USER);
        
        // Temporário
        if (dto.getEmail().endsWith("@globetechers.com")) {
            assignedRoles.add(RoleEnum.ROLE_ADMIN);
        }
        
        UsuarioModel novoUsuario = UsuarioModel.builder()
                .nome(dto.getNome())
                .email(emailVO)
                .senha(senhaVO)
                .areaAtuacao(dto.getAreaAtuacao())
                .nivelCarreira(dto.getNivelCarreira())
                .roles(assignedRoles) 
                .build();
        
        UsuarioModel usuarioSalvo = usuarioRepository.save(novoUsuario);

        String token = "FAKE_JWT_TOKEN_FOR_" + usuarioSalvo.getId();
       List<String> rolesString = usuarioSalvo.getRoles();

        return AuthResponseDTO.builder()
                .token(token)
                .idUsuario(usuarioSalvo.getId()) 
                .login(usuarioSalvo.getEmail().getValue())
                .roles(rolesString)
                .mensagem("Registro realizado com sucesso!")
                .build();
    }

    public AuthResponseDTO authenticateUser(LoginRequestDTO dto) {
        UsuarioModel usuario = usuarioRepository.findByEmailValue(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas.")); 

        String expectedHash = "FAKE_HASH_" + dto.getSenha();
        if (!usuario.getPassword().equals(expectedHash)) { 
             throw new RuntimeException("Credenciais inválidas.");
        }

        String token = "FAKE_JWT_TOKEN_FOR_" + usuario.getId();
        
        List<String> rolesString = usuario.getRoles();

        return AuthResponseDTO.builder()
                .token(token)
                .idUsuario(usuario.getId()) 
                .login(usuario.getEmail().getValue())
                .roles(rolesString)
                .mensagem("Login realizado com sucesso!")
                .build();
    }
}