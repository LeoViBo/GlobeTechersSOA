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