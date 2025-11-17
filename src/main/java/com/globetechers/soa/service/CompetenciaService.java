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