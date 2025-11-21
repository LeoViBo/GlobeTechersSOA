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
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "USUARIOSGT")
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
    @Size(min = 3, max = 100)
    @Column(name = "NOME")
    private String nome;

    @Embedded
    @NotNull
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "EMAIL", nullable = false, unique = true, length = 100))
    })
    private EmailVO email;

    @Embedded
    @NotNull
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "SENHA", nullable = false, length = 60))
    })
    private SenhaVO senha;

    @NotBlank
    @Column(name = "AREA_ATUACAO")
    private String areaAtuacao;

    @NotBlank
    @Column(name = "NIVEL_CARREIRA")
    private String nivelCarreira;

    @NotNull
    @Column(name = "DATA_CADASTRO")
    @Builder.Default
    private LocalDate dataCadastro = LocalDate.now();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "USUARIO_ROLES", joinColumns = @JoinColumn(name = "USUARIO_ID"))
    @Column(name = "ROLES")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<RoleEnum> roles = Collections.singleton(RoleEnum.ROLE_USER);

    @PrePersist
    public void prePersist() {
        if (this.dataCadastro == null) {
            this.dataCadastro = LocalDate.now();
        }
    }

    /**
     * Retorna as roles como lista de strings para popular DTOs.
     */
    public List<String> getRolesAsString() {
        if (roles == null) return List.of();
        return roles.stream().map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.senha.getValue();
    }

    @Override
    public String getUsername() {
        return this.email.getValue();
    }

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
        if (!(o instanceof UsuarioModel)) return false;
        UsuarioModel that = (UsuarioModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
