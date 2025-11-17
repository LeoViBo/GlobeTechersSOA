package com.globetechers.soa.repository;

import com.globetechers.soa.domain.model.CompetenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetenciaRepository extends JpaRepository<CompetenciaModel, Long> {
    
    Optional<CompetenciaModel> findByNome(String nome);
}