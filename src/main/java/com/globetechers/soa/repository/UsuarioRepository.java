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