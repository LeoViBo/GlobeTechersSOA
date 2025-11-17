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