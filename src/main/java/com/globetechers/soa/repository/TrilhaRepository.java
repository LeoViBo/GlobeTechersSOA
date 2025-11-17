package com.globetechers.soa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globetechers.soa.domain.model.TrilhaModel;

@Repository
public interface TrilhaRepository extends JpaRepository<TrilhaModel, Long> {
    
    Optional<TrilhaModel> findByNome(String nome);
}