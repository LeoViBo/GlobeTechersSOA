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