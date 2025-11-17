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