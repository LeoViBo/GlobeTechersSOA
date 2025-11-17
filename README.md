package com.globetechers.soa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globetechers.soa.dto.AuthResponseDTO;
import com.globetechers.soa.dto.LoginRequestDTO;
import com.globetechers.soa.dto.RegisterRequestDTO;
import com.globetechers.soa.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerUser(@Valid @RequestBody RegisterRequestDTO dto) {
        AuthResponseDTO response = authService.registerUser(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO dto) {
        AuthResponseDTO response = authService.authenticateUser(dto);
        return ResponseEntity.ok(response);
    }
}

package com.globetechers.soa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globetechers.soa.dto.CompetenciaRequestDTO;
import com.globetechers.soa.dto.CompetenciaResponseDTO;
import com.globetechers.soa.service.CompetenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/competencias")
public class CompetenciaController {

    private final CompetenciaService competenciaService;

    public CompetenciaController(CompetenciaService competenciaService) {
        this.competenciaService = competenciaService;
    }

    @PostMapping
    public ResponseEntity<CompetenciaResponseDTO> criarCompetencia(@Valid @RequestBody CompetenciaRequestDTO dto) {
        CompetenciaResponseDTO novaCompetencia = competenciaService.criarCompetencia(dto);
        return new ResponseEntity<>(novaCompetencia, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompetenciaResponseDTO>> listarTodas() {
        List<CompetenciaResponseDTO> competencias = competenciaService.listarTodas();
        return ResponseEntity.ok(competencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaResponseDTO> buscarPorId(@PathVariable Long id) {
        CompetenciaResponseDTO competencia = competenciaService.buscarPorId(id);
        return ResponseEntity.ok(competencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetenciaResponseDTO> atualizarCompetencia(@PathVariable Long id, 
                                                               @Valid @RequestBody CompetenciaRequestDTO dto) {
        CompetenciaResponseDTO competenciaAtualizada = competenciaService.atualizarCompetencia(id, dto);
        return ResponseEntity.ok(competenciaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCompetencia(@PathVariable Long id) {
        competenciaService.excluirCompetencia(id);
        return ResponseEntity.noContent().build();
    }
}

package com.globetechers.soa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globetechers.soa.dto.MatriculaRequestDTO;
import com.globetechers.soa.dto.MatriculaResponseDTO;
import com.globetechers.soa.service.MatriculaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> matricularUsuario(@Valid @RequestBody MatriculaRequestDTO dto) {
        MatriculaResponseDTO novaMatricula = matriculaService.matricularUsuario(dto);
        return new ResponseEntity<>(novaMatricula, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDTO>> listarTodas() {
        List<MatriculaResponseDTO> matriculas = matriculaService.listarTodas();
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponseDTO> buscarPorId(@PathVariable Long id) {
        MatriculaResponseDTO matricula = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(matricula);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MatriculaResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<MatriculaResponseDTO> matriculas = matriculaService.listarMatriculasPorUsuario(usuarioId);
        return ResponseEntity.ok(matriculas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaResponseDTO> atualizarMatricula(@PathVariable Long id, 
                                                               @Valid @RequestBody MatriculaRequestDTO dto) {
        MatriculaResponseDTO matriculaAtualizada = matriculaService.atualizarMatricula(id, dto);
        return ResponseEntity.ok(matriculaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMatricula(@PathVariable Long id) {
        matriculaService.excluirMatricula(id);
        return ResponseEntity.noContent().build();
    }
}

package com.globetechers.soa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globetechers.soa.dto.TrilhaCompetenciaRequestDTO;
import com.globetechers.soa.dto.TrilhaCompetenciaResponseDTO;
import com.globetechers.soa.service.TrilhaCompetenciaService;

import jakarta.validation.Valid;

class ResourceNotFoundException extends RuntimeException { public ResourceNotFoundException(String message) { super(message); } }
class BusinessException extends RuntimeException { public BusinessException(String message) { super(message); } }

@RestController
@RequestMapping("/api/trilhas-competencias")
public class TrilhaCompetenciaController {

    private final TrilhaCompetenciaService trilhaCompetenciaService;

    public TrilhaCompetenciaController(TrilhaCompetenciaService trilhaCompetenciaService) {
        this.trilhaCompetenciaService = trilhaCompetenciaService;
    }

    @PostMapping
    public ResponseEntity<TrilhaCompetenciaResponseDTO> create(@Valid @RequestBody TrilhaCompetenciaRequestDTO request) {
        try {
            TrilhaCompetenciaResponseDTO response = trilhaCompetenciaService.create(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaCompetenciaResponseDTO> findById(@PathVariable Long id) {
        try {
            TrilhaCompetenciaResponseDTO response = trilhaCompetenciaService.findById(id);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TrilhaCompetenciaResponseDTO>> findAll() {
        List<TrilhaCompetenciaResponseDTO> response = trilhaCompetenciaService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = "trilhaId")
    public ResponseEntity<List<TrilhaCompetenciaResponseDTO>> findByTrilhaId(@RequestParam Long trilhaId) {
        List<TrilhaCompetenciaResponseDTO> response = trilhaCompetenciaService.findByTrilhaId(trilhaId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = "competenciaId")
    public ResponseEntity<List<TrilhaCompetenciaResponseDTO>> findByCompetenciaId(@RequestParam Long competenciaId) {
        List<TrilhaCompetenciaResponseDTO> response = trilhaCompetenciaService.findByCompetenciaId(competenciaId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaCompetenciaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TrilhaCompetenciaRequestDTO request) {
        try {
            TrilhaCompetenciaResponseDTO response = trilhaCompetenciaService.update(id, request);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            trilhaCompetenciaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.globetechers.soa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globetechers.soa.dto.TrilhaRequestDTO;
import com.globetechers.soa.dto.TrilhaResponseDTO;
import com.globetechers.soa.service.TrilhaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trilhas")
public class TrilhaController {

    private final TrilhaService trilhaService;

    public TrilhaController(TrilhaService trilhaService) {
        this.trilhaService = trilhaService;
    }

    @PostMapping
    public ResponseEntity<TrilhaResponseDTO> criarTrilha(@Valid @RequestBody TrilhaRequestDTO dto) {
        TrilhaResponseDTO novaTrilha = trilhaService.criarTrilha(dto);
        return new ResponseEntity<>(novaTrilha, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrilhaResponseDTO>> listarTodas() {
        List<TrilhaResponseDTO> trilhas = trilhaService.listarTodas();
        return ResponseEntity.ok(trilhas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaResponseDTO> buscarPorId(@PathVariable Long id) {
        TrilhaResponseDTO trilha = trilhaService.buscarPorId(id);
        return ResponseEntity.ok(trilha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaResponseDTO> atualizarTrilha(@PathVariable Long id, 
                                                               @Valid @RequestBody TrilhaRequestDTO dto) {
        TrilhaResponseDTO trilhaAtualizada = trilhaService.atualizarTrilha(id, dto);
        return ResponseEntity.ok(trilhaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTrilha(@PathVariable Long id) {
        trilhaService.excluirTrilha(id);
        return ResponseEntity.noContent().build();
    }
}

package com.globetechers.soa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globetechers.soa.dto.UsuarioRequestDTO;
import com.globetechers.soa.dto.UsuarioResponseDTO;
import com.globetechers.soa.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(
            @Validated(UsuarioRequestDTO.Creation.class) @RequestBody UsuarioRequestDTO dto) {
        
        UsuarioResponseDTO response = usuarioService.criarUsuario(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> response = usuarioService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        UsuarioResponseDTO response = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable Long id,
            @Validated(UsuarioRequestDTO.Update.class) @RequestBody UsuarioRequestDTO dto) {
        
        UsuarioResponseDTO response = usuarioService.atualizarUsuario(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

package com.globetechers.soa.domain.enums;

public enum NivelEnum {
    INICIANTE,
    INTERMEDIARIO,
    AVANCADO,
    TODOS
}

package com.globetechers.soa.domain.enums;

public enum RoleEnum {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_MANAGER
}

package com.globetechers.soa.domain.enums;

public enum TrilhaStatusEnum {
    ATIVA,
    INATIVA,
    EM_DESENVOLVIMENTO
}

package com.globetechers.soa.domain.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class EmailVO {

    @Email(message = "O formato do e-mail é inválido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    @Column(name = "email", nullable = false, unique = true, length = 100) 
    private String value;

    protected EmailVO() {}

    public EmailVO(String value) { this.value = value; }

    public String getValue() { return value; }

    @Override
    public String toString() { return value; }
}

package com.globetechers.soa.domain.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class SenhaVO {

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 60, max = 60, message = "A senha deve ser armazenada com 60 caracteres (BCrypt).")
    @Column(name = "senha", nullable = false, length = 60)
    private String value;

    protected SenhaVO() {}

    public SenhaVO(String value) { this.value = value; }

    public String getValue() { return value; }
}

package com.globetechers.soa.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TB_COMPETENCIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nome;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Column(nullable = false, length = 100)
    private String areaConhecimento; 
}

package com.globetechers.soa.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "matriculas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trilha_id", nullable = false)
    private TrilhaModel trilha;

    @Column(name = "data_inscricao", nullable = false)
    private LocalDate dataInscricao;

    @Column(length = 50, nullable = false)
    private String status;

    @ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(
        name = "matricula_competencia",
        joinColumns = @JoinColumn(name = "matricula_id"),
        inverseJoinColumns = @JoinColumn(name = "competencia_id")
    )
    private Set<CompetenciaModel> competencias;
}

package com.globetechers.soa.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_TRILHA_COMPETENCIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaCompetenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trilha_id", nullable = false)
    private TrilhaModel trilha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competencia_id", nullable = false)
    private CompetenciaModel competencia;

    @Column(nullable = true)
    private Integer nivelEsperado;
}

package com.globetechers.soa.domain.model;

import com.globetechers.soa.domain.enums.NivelEnum;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "trilhas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da trilha é obrigatório.")
    @Size(min = 5, max = 150, message = "O nome deve ter entre 5 e 150 caracteres.")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "O nível é obrigatório.")
    @Enumerated(EnumType.STRING)
    private NivelEnum nivel;

    @NotNull(message = "A carga horária é obrigatória.")
    @Min(value = 1, message = "A carga horária deve ser positiva.")
    private Integer cargaHoraria;

    @NotBlank(message = "O foco principal é obrigatório.")
    private String focoPrincipal;
    
    private LocalDate dataCriacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criador_id", nullable = false)
    private UsuarioModel criador;
    
    @ManyToMany
    @JoinTable(
        name = "trilha_competencia", 
        joinColumns = @JoinColumn(name = "trilha_id"),
        inverseJoinColumns = @JoinColumn(name = "competencia_id")
    )
    private Set<CompetenciaModel> competencias;

    private String objetivoCarreira;
}

package com.globetechers.soa.domain.model;

import com.globetechers.soa.domain.enums.RoleEnum;
import com.globetechers.soa.domain.model.vo.EmailVO;
import com.globetechers.soa.domain.model.vo.SenhaVO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String nome;

    @Embedded
    @NotNull(message = "O Value Object de Email é obrigatório.")
    private EmailVO email;

    @Embedded
    @NotNull(message = "O Value Object de Senha é obrigatório.")
    private SenhaVO senha;

    @NotBlank(message = "A área de atuação é obrigatória.")
    private String areaAtuacao;

    @NotBlank(message = "O nível de carreira é obrigatória.")
    private String nivelCarreira;

    @NotNull(message = "A data de cadastro é obrigatória.")
    private LocalDate dataCadastro = LocalDate.now();
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Enumerated(EnumType.STRING)
    @Builder.Default 
    private Set<RoleEnum> roles = Collections.singleton(RoleEnum.ROLE_USER);
    public List<String> getRoles() {
        return this.roles.stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
    
    public String getNomeCompleto() {
        return this.nome;
    }

    @Override
    public String getPassword() { return this.senha.getValue(); }

    @Override
    public String getUsername() { return this.email.getValue(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioModel that = (UsuarioModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}

package com.globetechers.soa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequestDTO {

    @NotBlank(message = "O login (email) é obrigatório.")
    private String login;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;
}

package com.globetechers.soa.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {

    private String token;

    @Builder.Default
    private String tipo = "Bearer";

    private Long idUsuario;

    private String login;

    private List<String> roles;

    private String mensagem;
}

package com.globetechers.soa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompetenciaRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome não pode exceder 100 caracteres")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 1000, message = "A descrição não pode exceder 1000 caracteres")
    private String descricao;

    @NotBlank(message = "A categoria é obrigatória")
    @Size(max = 50, message = "A categoria não pode exceder 50 caracteres")
    private String categoria;

    @NotBlank(message = "A área de conhecimento é obrigatória")
    @Size(max = 100, message = "A área de conhecimento não pode exceder 100 caracteres")
    private String areaConhecimento;
}

package com.globetechers.soa.dto;

import com.globetechers.soa.domain.model.CompetenciaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetenciaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String categoria; 
    private String areaConhecimento;

    public static CompetenciaResponseDTO fromModel(CompetenciaModel model) {
        if (model == null) return null;
        return CompetenciaResponseDTO.builder()
                .id(model.getId())
                .nome(model.getNome())
                .descricao(model.getDescricao())
                .categoria(model.getCategoria())
                .areaConhecimento(model.getAreaConhecimento()) 
                .build();
    }
}

package com.globetechers.soa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser um endereço de email válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;
}

package com.globetechers.soa.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório.")
    @Positive(message = "O ID do usuário deve ser um valor positivo.")
    private Long usuarioId;

    @NotNull(message = "O ID da trilha é obrigatório.")
    @Positive(message = "O ID da trilha deve ser um valor positivo.")
    private Long trilhaId;
    
    @NotEmpty(message = "A lista de IDs de competências não pode ser vazia.")
    private Set<Long> idsCompetencias;

    private String status;
}

package com.globetechers.soa.dto;

import com.globetechers.soa.domain.model.MatriculaModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatriculaResponseDTO {

    private Long id;
    private Long usuarioId;
    private String usuarioNome;
    private Long trilhaId;
    private String trilhaNome;
    private LocalDate dataInscricao;
    private String status;

    public static MatriculaResponseDTO fromModel(MatriculaModel model) {
        return MatriculaResponseDTO.builder()
                .id(model.getId())
                .usuarioId(model.getUsuario().getId())
                .usuarioNome(model.getUsuario().getNomeCompleto())
                .trilhaId(model.getTrilha().getId())
                .trilhaNome(model.getTrilha().getNome()) 
                .dataInscricao(model.getDataInscricao())
                .status(model.getStatus())
                .build();
    }
}

package com.globetechers.soa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {

    @NotBlank(message = "O nome completo é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser um endereço de email válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String senha;

    @NotBlank(message = "A área de atuação é obrigatória.")
    private String areaAtuacao;

    @NotBlank(message = "O nível de carreira é obrigatório.")
    private String nivelCarreira;
}

package com.globetechers.soa.dto;

import org.springframework.lang.Nullable;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaCompetenciaRequestDTO {

    @NotNull(message = "O ID da Trilha é obrigatório.")
    private Long trilhaId;

    @NotNull(message = "O ID da Competência é obrigatório.")
    private Long competenciaId;

    @Nullable 
    private Integer nivelEsperado;
}

package com.globetechers.soa.dto;

import com.globetechers.soa.domain.model.TrilhaCompetenciaModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrilhaCompetenciaResponseDTO {

    private Long id;
    private TrilhaResponseDTO trilha;
    private CompetenciaResponseDTO competencia;
    private Integer nivelEsperado;

    public static TrilhaCompetenciaResponseDTO fromModel(TrilhaCompetenciaModel model) {
        return TrilhaCompetenciaResponseDTO.builder()
                .id(model.getId())
                .trilha(TrilhaResponseDTO.fromModel(model.getTrilha())) 
                .competencia(CompetenciaResponseDTO.fromModel(model.getCompetencia()))
                .nivelEsperado(model.getNivelEsperado())
                .build();
    }
}

package com.globetechers.soa.dto;

import com.globetechers.soa.domain.enums.NivelEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaRequestDTO {

    @NotBlank(message = "O nome da Trilha é obrigatório.")
    @Size(min = 5, max = 150, message = "O nome deve ter entre 5 e 150 caracteres.")
    private String nome;

    @NotBlank(message = "A descrição da Trilha é obrigatória.")
    @Size(min = 20, max = 500, message = "A descrição deve ter entre 20 e 500 caracteres.")
    private String descricao;

    @NotNull(message = "O nível é obrigatório.")
    private NivelEnum nivel;

    @NotNull(message = "A carga horária é obrigatória.")
    @Min(value = 1, message = "A carga horária deve ser positiva.")
    private Integer cargaHoraria;

    @NotBlank(message = "O foco principal é obrigatório.")
    @Size(max = 100, message = "O foco principal não pode exceder 100 caracteres.")
    private String focoPrincipal;

    @NotNull(message = "O ID do criador é obrigatório.")
    @Min(value = 1, message = "O ID do criador deve ser um valor positivo.")
    private Long criadorId;
}

package com.globetechers.soa.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.globetechers.soa.domain.model.CompetenciaModel;
import com.globetechers.soa.domain.model.TrilhaModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrilhaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String objetivoCarreira;
    private LocalDate dataCriacao;
    private Long criadorId;

    private List<CompetenciaResumoDTO> competencias;

    public static TrilhaResponseDTO fromModel(TrilhaModel model) {
        List<CompetenciaResumoDTO> competenciasResumo = model.getCompetencias().stream()
                .map(CompetenciaResumoDTO::fromModel)
                .collect(Collectors.toList());

        return TrilhaResponseDTO.builder()
                .id(model.getId())
                .nome(model.getNome())
                .descricao(model.getDescricao())
                .objetivoCarreira(model.getObjetivoCarreira())
                .dataCriacao(model.getDataCriacao())
                .criadorId(model.getCriador().getId())
                .competencias(competenciasResumo)
                .build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CompetenciaResumoDTO {
        private Long id;
        private String nome;
        private String area;

        public static CompetenciaResumoDTO fromModel(CompetenciaModel model) {
            return CompetenciaResumoDTO.builder()
                    .id(model.getId())
                    .nome(model.getNome())
                    .area(model.getCategoria())
                    .build();
        }
    }
}

package com.globetechers.soa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O formato do email é inválido.")
    @Size(max = 150, message = "O email não pode exceder 150 caracteres.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.", groups = {Creation.class})
    @Size(min = 6, max = 255, message = "A senha deve ter no mínimo 6 caracteres.")
    private String senha;

    @NotBlank(message = "A área de atuação é obrigatória.")
    private String areaAtuacao;

    @NotBlank(message = "O nível de carreira é obrigatório.")
    private String nivelCarreira;

    private LocalDate dataCadastro;

    public interface Creation {}
    public interface Update {}
}

package com.globetechers.soa.dto;

import com.globetechers.soa.domain.model.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private Long id;
    private String nomeCompleto;
    private String email;
    private String areaAtuacao; 
    private String nivelCarreira;

    public static UsuarioResponseDTO fromModel(UsuarioModel model) {
        if (model == null) return null;
        return UsuarioResponseDTO.builder()
                .id(model.getId())
                .nomeCompleto(model.getNomeCompleto())
                .email(model.getUsername())
                .areaAtuacao(model.getAreaAtuacao())
                .nivelCarreira(model.getNivelCarreira())
                .build();
    }
}

package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {


    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CompetenciaJaExisteException extends RuntimeException {
    public CompetenciaJaExisteException(String nome) {
        super("A Competência com o nome '" + nome + "' já está cadastrada no sistema.");
    }
}

package com.globetechers.soa.exception;

public class CompetenciaNaoEncontradaException extends RuntimeException {
    public CompetenciaNaoEncontradaException(Long id) {
        super("Competência não encontrada com o ID: " + id);
    }
}

package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailJaCadastradoException extends RuntimeException {

    public EmailJaCadastradoException(String email) {
        super("O e-mail '" + email + "' já está cadastrado no sistema.");
    }
}

package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MatriculaJaExisteException extends RuntimeException {

    public MatriculaJaExisteException(Long usuarioId, Long trilhaId) {
        super("O usuário " + usuarioId + " já está matriculado na trilha " + trilhaId + ".");
    }
}

package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MatriculaNaoEncontradaException extends RuntimeException {

    public MatriculaNaoEncontradaException(Long id) {
        super("Matrícula com ID " + id + " não encontrada.");
    }
}

package com.globetechers.soa.exception;

public class TrilhaCompetenciaJaExisteException extends RuntimeException {
    public TrilhaCompetenciaJaExisteException(Long trilhaId, Long competenciaId) {
        super("O vínculo entre Trilha ID " + trilhaId + " e Competência ID " + competenciaId + " já existe.");
    }
}

package com.globetechers.soa.exception;

public class TrilhaCompetenciaNaoEncontradaException extends RuntimeException {
    public TrilhaCompetenciaNaoEncontradaException(Long id) {
        super("Vínculo Trilha-Competência não encontrado com o ID: " + id);
    }
}

package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TrilhaJaExisteException extends RuntimeException {
    public TrilhaJaExisteException(String nome) {
        super("A Trilha de Aprendizagem com o nome '" + nome + "' já está cadastrada no sistema.");
    }
}


package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrilhaNaoEncontradaException extends RuntimeException {

    public TrilhaNaoEncontradaException(Long id) {
        super("Trilha de Aprendizagem com ID " + id + " não encontrada.");
    }
}

package com.globetechers.soa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário com ID " + id + " não encontrado.");
    }
}

package com.globetechers.soa.repository;

import com.globetechers.soa.domain.model.CompetenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetenciaRepository extends JpaRepository<CompetenciaModel, Long> {
    
    Optional<CompetenciaModel> findByNome(String nome);
}

package com.globetechers.soa.repository;

import com.globetechers.soa.domain.model.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaModel, Long> {

    Optional<MatriculaModel> findByUsuarioIdAndTrilhaId(Long usuarioId, Long trilhaId);
    
    List<MatriculaModel> findByUsuarioId(Long usuarioId);
}

package com.globetechers.soa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globetechers.soa.domain.model.TrilhaCompetenciaModel;

@Repository
public interface TrilhaCompetenciaRepository extends JpaRepository<TrilhaCompetenciaModel, Long> {
    
    List<TrilhaCompetenciaModel> findByTrilhaId(Long trilhaId);

    List<TrilhaCompetenciaModel> findByCompetenciaId(Long competenciaId); 

    Optional<TrilhaCompetenciaModel> findByTrilhaIdAndCompetenciaId(Long trilhaId, Long competenciaId);
}

package com.globetechers.soa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globetechers.soa.domain.model.TrilhaModel;

@Repository
public interface TrilhaRepository extends JpaRepository<TrilhaModel, Long> {
    
    Optional<TrilhaModel> findByNome(String nome);
}

package com.globetechers.soa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globetechers.soa.domain.model.UsuarioModel;
import com.globetechers.soa.domain.model.vo.EmailVO;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByEmailValue(String email); 

    boolean existsByEmailValue(String email);

    Optional<UsuarioModel> findByEmail(EmailVO email);

}

package com.globetechers.soa.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        
        logger.error("Acesso não autorizado. Erro: {}", authException.getMessage());
        
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso negado. Você precisa de um token JWT válido.");
    }
}

package com.globetechers.soa.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwt = getJwtFromRequest(request);

            if (jwt != null && jwtService.validateToken(jwt)) {
                String username = jwtService.getUsernameFromToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Não foi possível definir a autenticação do usuário no contexto de segurança", ex);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

package com.globetechers.soa.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.globetechers.soa.domain.model.UsuarioModel;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    public String generateToken(Authentication authentication) {
        UsuarioModel usuarioPrincipal = (UsuarioModel) authentication.getPrincipal(); 

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        Key key = key();

        return Jwts.builder()
                .setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
     public String generateToken(UserDetails userDetails) {
        String subject = userDetails.getUsername(); 

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        Key key = key();

        return Jwts.builder()
                .setSubject(subject) 
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token JWT inválido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token JWT não suportado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("A string de claims JWT está vazia: {}", e.getMessage());
        } catch (io.jsonwebtoken.security.SignatureException e) {
            logger.error("Assinatura JWT inválida: {}", e.getMessage());
        }
        return false;
    }
}

package com.globetechers.soa.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.globetechers.soa.domain.model.UsuarioModel;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    public String generateToken(Authentication authentication) {
        UsuarioModel usuarioPrincipal = (UsuarioModel) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        Key key = key();

        return Jwts.builder()
                .setSubject(usuarioPrincipal.getUsername()) 
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token JWT inválido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token JWT não suportado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("A string de claims JWT está vazia: {}", e.getMessage());
        } catch (io.jsonwebtoken.security.SignatureException e) {
            logger.error("Assinatura JWT inválida: {}", e.getMessage());
        }
        return false;
    }
}

package com.globetechers.soa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.globetechers.soa.security.jwt.JwtAuthEntryPoint;
import com.globetechers.soa.security.jwt.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final JwtAuthEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, JwtAuthEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)

            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            )

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/auth/**", 
                    "/h2-console/**",
                    "/v3/api-docs/**", 
                    "/swagger-ui/**"
                ).permitAll()
                
                .anyRequest().authenticated()
            )

            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

package com.globetechers.soa.security;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.globetechers.soa.repository.UsuarioRepository;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmailValue(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}

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

package com.globetechers.soa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globetechers.soa.domain.model.CompetenciaModel;
import com.globetechers.soa.dto.CompetenciaRequestDTO;
import com.globetechers.soa.dto.CompetenciaResponseDTO;
import com.globetechers.soa.exception.CompetenciaJaExisteException;
import com.globetechers.soa.exception.CompetenciaNaoEncontradaException;
import com.globetechers.soa.repository.CompetenciaRepository;

@Service
public class CompetenciaService {

    private final CompetenciaRepository competenciaRepository;

    public CompetenciaService(CompetenciaRepository competenciaRepository) {
        this.competenciaRepository = competenciaRepository;
    }

    @Transactional
    public CompetenciaResponseDTO criarCompetencia(CompetenciaRequestDTO dto) {
        competenciaRepository.findByNome(dto.getNome())
                .ifPresent(c -> {
                    throw new CompetenciaJaExisteException(dto.getNome());
                });

        CompetenciaModel novaCompetencia = CompetenciaModel.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .categoria(dto.getCategoria())
                .areaConhecimento(dto.getAreaConhecimento())
                .build();
        
        CompetenciaModel competenciaSalva = competenciaRepository.save(novaCompetencia);
        
        return CompetenciaResponseDTO.fromModel(competenciaSalva);
    }

    public CompetenciaResponseDTO buscarPorId(Long id) {
        CompetenciaModel competencia = buscarModelPorId(id);
        return CompetenciaResponseDTO.fromModel(competencia);
    }

    public CompetenciaModel buscarModelPorId(Long id) {
        return competenciaRepository.findById(id)
                .orElseThrow(() -> new CompetenciaNaoEncontradaException(id));
    }

    public List<CompetenciaResponseDTO> listarTodas() {
        return competenciaRepository.findAll().stream()
                .map(CompetenciaResponseDTO::fromModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public CompetenciaResponseDTO atualizarCompetencia(Long id, CompetenciaRequestDTO dto) {
        CompetenciaModel competenciaExistente = buscarModelPorId(id);
        
        if (!competenciaExistente.getNome().equals(dto.getNome())) {
            competenciaRepository.findByNome(dto.getNome())
                    .ifPresent(c -> {
                        throw new CompetenciaJaExisteException(dto.getNome());
                    });
        }
        
        BeanUtils.copyProperties(dto, competenciaExistente, "id");
        
        CompetenciaModel competenciaAtualizada = competenciaRepository.save(competenciaExistente);
        return CompetenciaResponseDTO.fromModel(competenciaAtualizada);
    }

    @Transactional
    public void excluirCompetencia(Long id) {
        CompetenciaModel competencia = buscarModelPorId(id); 
        competenciaRepository.delete(competencia);
    }
}

package com.globetechers.soa.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globetechers.soa.domain.model.CompetenciaModel;
import com.globetechers.soa.domain.model.MatriculaModel;
import com.globetechers.soa.domain.model.TrilhaModel;
import com.globetechers.soa.domain.model.UsuarioModel;
import com.globetechers.soa.dto.MatriculaRequestDTO;
import com.globetechers.soa.dto.MatriculaResponseDTO;
import com.globetechers.soa.exception.BusinessException;
import com.globetechers.soa.exception.MatriculaJaExisteException;
import com.globetechers.soa.exception.MatriculaNaoEncontradaException;
import com.globetechers.soa.repository.MatriculaRepository;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final UsuarioService usuarioService;
    private final TrilhaService trilhaService;
    private final CompetenciaService competenciaService;

    public MatriculaService(MatriculaRepository matriculaRepository,
                            UsuarioService usuarioService,
                            TrilhaService trilhaService,
                            CompetenciaService competenciaService) { 
        this.matriculaRepository = matriculaRepository;
        this.usuarioService = usuarioService;
        this.trilhaService = trilhaService;
        this.competenciaService = competenciaService; 
    }

    @Transactional
    public MatriculaResponseDTO matricularUsuario(MatriculaRequestDTO dto) {
        Long usuarioId = dto.getUsuarioId();
        Long trilhaId = dto.getTrilhaId();

        if (matriculaRepository.findByUsuarioIdAndTrilhaId(usuarioId, trilhaId).isPresent()) {
            throw new MatriculaJaExisteException(usuarioId, trilhaId);
        }

        UsuarioModel usuario = usuarioService.buscarModelPorId(usuarioId); 
        TrilhaModel trilha = trilhaService.buscarModelPorId(trilhaId);

        Set<CompetenciaModel> competencias = new HashSet<>();
        
        for (Long competenciaId : dto.getIdsCompetencias()) { 
            competencias.add(competenciaService.buscarModelPorId(competenciaId));
        }

        MatriculaModel novaMatricula = MatriculaModel.builder()
                .usuario(usuario)
                .trilha(trilha)
                .competencias(competencias)
                .dataInscricao(LocalDate.now())
                .status(dto.getStatus() != null ? dto.getStatus() : "INSCRITO")
                .build();
        
        MatriculaModel matriculaSalva = matriculaRepository.save(novaMatricula);
        return MatriculaResponseDTO.fromModel(matriculaSalva);
    }
    
    @Transactional
    public MatriculaResponseDTO atualizarMatricula(Long id, MatriculaRequestDTO dto) {
        MatriculaModel matriculaExistente = buscarModelPorId(id);
        
        if (!matriculaExistente.getUsuario().getId().equals(dto.getUsuarioId()) ||
            !matriculaExistente.getTrilha().getId().equals(dto.getTrilhaId())) {
             throw new BusinessException("Não é permitido alterar o Usuário ou a Trilha de uma matrícula existente.");
        }

        if (dto.getStatus() != null && !dto.getStatus().isEmpty()) {
            matriculaExistente.setStatus(dto.getStatus());
        }

        if (dto.getIdsCompetencias() != null) {
              Set<CompetenciaModel> novasCompetencias = dto.getIdsCompetencias().stream()
                  .map(competenciaService::buscarModelPorId) 
                  .collect(Collectors.toSet());
            matriculaExistente.setCompetencias(novasCompetencias);
        }
        
        MatriculaModel matriculaAtualizada = matriculaRepository.save(matriculaExistente);
        return MatriculaResponseDTO.fromModel(matriculaAtualizada);
    }

    public MatriculaResponseDTO buscarPorId(Long id) {
        MatriculaModel matricula = buscarModelPorId(id);
        return MatriculaResponseDTO.fromModel(matricula);
    }

    public List<MatriculaResponseDTO> listarMatriculasPorUsuario(Long usuarioId) {
        return matriculaRepository.findByUsuarioId(usuarioId).stream()
                .map(MatriculaResponseDTO::fromModel)
                .collect(Collectors.toList());
    }
    
    private MatriculaModel buscarModelPorId(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new MatriculaNaoEncontradaException(id));
    }

    public List<MatriculaResponseDTO> listarTodas() {
        return matriculaRepository.findAll().stream()
                .map(MatriculaResponseDTO::fromModel)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void excluirMatricula(Long id) {
        MatriculaModel matricula = buscarModelPorId(id);
        matriculaRepository.delete(matricula);
    }
}

package com.globetechers.soa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globetechers.soa.domain.model.TrilhaCompetenciaModel;
import com.globetechers.soa.dto.TrilhaCompetenciaRequestDTO;
import com.globetechers.soa.dto.TrilhaCompetenciaResponseDTO;
import com.globetechers.soa.exception.BusinessException; 
import com.globetechers.soa.exception.CompetenciaNaoEncontradaException; 
import com.globetechers.soa.exception.TrilhaNaoEncontradaException; 
import com.globetechers.soa.exception.TrilhaCompetenciaNaoEncontradaException; 
import com.globetechers.soa.repository.CompetenciaRepository;
import com.globetechers.soa.repository.TrilhaCompetenciaRepository;
import com.globetechers.soa.repository.TrilhaRepository;

@Service
public class TrilhaCompetenciaService {

    private final TrilhaCompetenciaRepository trilhaCompetenciaRepository;
    private final TrilhaRepository trilhaRepository;
    private final CompetenciaRepository competenciaRepository;

    public TrilhaCompetenciaService(
            TrilhaCompetenciaRepository trilhaCompetenciaRepository,
            TrilhaRepository trilhaRepository,
            CompetenciaRepository competenciaRepository) {
        this.trilhaCompetenciaRepository = trilhaCompetenciaRepository;
        this.trilhaRepository = trilhaRepository;
        this.competenciaRepository = competenciaRepository;
    }

    @Transactional
    public TrilhaCompetenciaResponseDTO create(TrilhaCompetenciaRequestDTO request) {
        Long trilhaId = request.getTrilhaId();
        Long competenciaId = request.getCompetenciaId();

        var trilhaModel = trilhaRepository.findById(trilhaId)
                .orElseThrow(() -> new TrilhaNaoEncontradaException(trilhaId)); 

        var competenciaModel = competenciaRepository.findById(competenciaId)
                .orElseThrow(() -> new CompetenciaNaoEncontradaException(competenciaId));

        trilhaCompetenciaRepository.findByTrilhaIdAndCompetenciaId(trilhaId, competenciaId)
                .ifPresent(assoc -> {
                    throw new BusinessException("A Competência com ID " + competenciaId + " já está associada à Trilha com ID " + trilhaId);
                });

        var newAssoc = TrilhaCompetenciaModel.builder()
                .trilha(trilhaModel)
                .competencia(competenciaModel)
                .nivelEsperado(request.getNivelEsperado())
                .build();

        newAssoc = trilhaCompetenciaRepository.save(newAssoc);

        return TrilhaCompetenciaResponseDTO.fromModel(newAssoc);
    }

    public TrilhaCompetenciaResponseDTO findById(Long id) {
        TrilhaCompetenciaModel model = trilhaCompetenciaRepository.findById(id)
                .orElseThrow(() -> new TrilhaCompetenciaNaoEncontradaException(id));

        return TrilhaCompetenciaResponseDTO.fromModel(model);
    }

    public List<TrilhaCompetenciaResponseDTO> findAll() {
        return trilhaCompetenciaRepository.findAll().stream()
                .map(TrilhaCompetenciaResponseDTO::fromModel)
                .collect(Collectors.toList());
    }

    public List<TrilhaCompetenciaResponseDTO> findByTrilhaId(Long trilhaId) {
        if (!trilhaRepository.existsById(trilhaId)) {
            throw new TrilhaNaoEncontradaException(trilhaId); 
        }
        
        return trilhaCompetenciaRepository.findByTrilhaId(trilhaId).stream()
                .map(TrilhaCompetenciaResponseDTO::fromModel)
                .collect(Collectors.toList());
    }

    public List<TrilhaCompetenciaResponseDTO> findByCompetenciaId(Long competenciaId) {
        if (!competenciaRepository.existsById(competenciaId)) {
            throw new CompetenciaNaoEncontradaException(competenciaId); 
        }
        
        return trilhaCompetenciaRepository.findByCompetenciaId(competenciaId).stream()
                .map(TrilhaCompetenciaResponseDTO::fromModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public TrilhaCompetenciaResponseDTO update(Long id, TrilhaCompetenciaRequestDTO request) {
        TrilhaCompetenciaModel existingModel = trilhaCompetenciaRepository.findById(id)
                .orElseThrow(() -> new TrilhaCompetenciaNaoEncontradaException(id));

        if (!existingModel.getTrilha().getId().equals(request.getTrilhaId()) || 
            !existingModel.getCompetencia().getId().equals(request.getCompetenciaId())) {
                throw new BusinessException("Não é permitido alterar a Trilha ou a Competência de uma associação existente. Use o DELETE e o CREATE para isso.");
        }

        existingModel.setNivelEsperado(request.getNivelEsperado());

        TrilhaCompetenciaModel updatedModel = trilhaCompetenciaRepository.save(existingModel);
        return TrilhaCompetenciaResponseDTO.fromModel(updatedModel);
    }

    @Transactional
    public void delete(Long id) {
        findById(id); 
        trilhaCompetenciaRepository.deleteById(id);
    }
}

package com.globetechers.soa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globetechers.soa.domain.model.TrilhaModel;
import com.globetechers.soa.dto.TrilhaRequestDTO;
import com.globetechers.soa.dto.TrilhaResponseDTO;
import com.globetechers.soa.exception.TrilhaJaExisteException;
import com.globetechers.soa.exception.TrilhaNaoEncontradaException;
import com.globetechers.soa.repository.TrilhaRepository;

@Service
public class TrilhaService {

    private final TrilhaRepository trilhaRepository;

    public TrilhaService(TrilhaRepository trilhaRepository) {
        this.trilhaRepository = trilhaRepository;
    }

    @Transactional
    public TrilhaResponseDTO criarTrilha(TrilhaRequestDTO dto) {
        trilhaRepository.findByNome(dto.getNome())
                .ifPresent(t -> {
                    throw new TrilhaJaExisteException(dto.getNome());
                });
        
        TrilhaModel novaTrilha = TrilhaModel.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .nivel(dto.getNivel())
                .cargaHoraria(dto.getCargaHoraria())
                .focoPrincipal(dto.getFocoPrincipal())
                .build();
        
        TrilhaModel trilhaSalva = trilhaRepository.save(novaTrilha);
        
        return TrilhaResponseDTO.fromModel(trilhaSalva);
    }

    public TrilhaResponseDTO buscarPorId(Long id) {
        TrilhaModel trilha = buscarModelPorId(id);
        return TrilhaResponseDTO.fromModel(trilha);
    }

    public TrilhaModel buscarModelPorId(Long id) {
        return trilhaRepository.findById(id)
                .orElseThrow(() -> new TrilhaNaoEncontradaException(id));
    }

    public List<TrilhaResponseDTO> listarTodas() {
        return trilhaRepository.findAll().stream()
                .map(TrilhaResponseDTO::fromModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public TrilhaResponseDTO atualizarTrilha(Long id, TrilhaRequestDTO dto) {
        TrilhaModel trilhaExistente = buscarModelPorId(id);
        
        if (!trilhaExistente.getNome().equals(dto.getNome())) {
             trilhaRepository.findByNome(dto.getNome())
                .ifPresent(t -> {
                    throw new TrilhaJaExisteException(dto.getNome());
                });
        }
        
        BeanUtils.copyProperties(dto, trilhaExistente, "id");
        
        TrilhaModel trilhaAtualizada = trilhaRepository.save(trilhaExistente);
        return TrilhaResponseDTO.fromModel(trilhaAtualizada);
    }

    @Transactional
    public void excluirTrilha(Long id) {
        TrilhaModel trilha = buscarModelPorId(id); 
        trilhaRepository.delete(trilha);
    }
}

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
                .roles(usuario.getRoles()) 
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
