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