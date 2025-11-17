package com.globetechers.soa.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globetechers.soa.domain.model.CompetenciaModel;
import com.globetechers.soa.domain.model.MatriculaModel;
import com.globetechers.soa.domain.model.TrilhaModel;
import com.globetechers.soa.domain.model.UsuarioModel;
import com.globetechers.soa.dto.MatriculaRequestDTO;
import com.globetechers.soa.dto.MatriculaResponseDTO;
import com.globetechers.soa.exception.BusinessException;
import com.globetechers.soa.exception.MatriculaJaExisteException;
import com.globetechers.soa.exception.MatriculaNaoEncontradaException;
import com.globetechers.soa.repository.MatriculaRepository;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final UsuarioService usuarioService;
    private final TrilhaService trilhaService;
    private final CompetenciaService competenciaService;

    public MatriculaService(MatriculaRepository matriculaRepository,
                            UsuarioService usuarioService,
                            TrilhaService trilhaService,
                            CompetenciaService competenciaService) { 
        this.matriculaRepository = matriculaRepository;
        this.usuarioService = usuarioService;
        this.trilhaService = trilhaService;
        this.competenciaService = competenciaService; 
    }

    @Transactional
    public MatriculaResponseDTO matricularUsuario(MatriculaRequestDTO dto) {
        Long usuarioId = dto.getUsuarioId();
        Long trilhaId = dto.getTrilhaId();

        if (matriculaRepository.findByUsuarioIdAndTrilhaId(usuarioId, trilhaId).isPresent()) {
            throw new MatriculaJaExisteException(usuarioId, trilhaId);
        }

        UsuarioModel usuario = usuarioService.buscarModelPorId(usuarioId); 
        TrilhaModel trilha = trilhaService.buscarModelPorId(trilhaId);

        Set<CompetenciaModel> competencias = new HashSet<>();
        
        for (Long competenciaId : dto.getIdsCompetencias()) { 
            competencias.add(competenciaService.buscarModelPorId(competenciaId));
        }

        MatriculaModel novaMatricula = MatriculaModel.builder()
                .usuario(usuario)
                .trilha(trilha)
                .competencias(competencias)
                .dataInscricao(LocalDate.now())
                .status(dto.getStatus() != null ? dto.getStatus() : "INSCRITO")
                .build();
        
        MatriculaModel matriculaSalva = matriculaRepository.save(novaMatricula);
        return MatriculaResponseDTO.fromModel(matriculaSalva);
    }
    
    @Transactional
    public MatriculaResponseDTO atualizarMatricula(Long id, MatriculaRequestDTO dto) {
        MatriculaModel matriculaExistente = buscarModelPorId(id);
        
        if (!matriculaExistente.getUsuario().getId().equals(dto.getUsuarioId()) ||
            !matriculaExistente.getTrilha().getId().equals(dto.getTrilhaId())) {
             throw new BusinessException("Não é permitido alterar o Usuário ou a Trilha de uma matrícula existente.");
        }

        if (dto.getStatus() != null && !dto.getStatus().isEmpty()) {
            matriculaExistente.setStatus(dto.getStatus());
        }

        if (dto.getIdsCompetencias() != null) {
              Set<CompetenciaModel> novasCompetencias = dto.getIdsCompetencias().stream()
                  .map(competenciaService::buscarModelPorId) 
                  .collect(Collectors.toSet());
            matriculaExistente.setCompetencias(novasCompetencias);
        }
        
        MatriculaModel matriculaAtualizada = matriculaRepository.save(matriculaExistente);
        return MatriculaResponseDTO.fromModel(matriculaAtualizada);
    }

    public MatriculaResponseDTO buscarPorId(Long id) {
        MatriculaModel matricula = buscarModelPorId(id);
        return MatriculaResponseDTO.fromModel(matricula);
    }

    public List<MatriculaResponseDTO> listarMatriculasPorUsuario(Long usuarioId) {
        return matriculaRepository.findByUsuarioId(usuarioId).stream()
                .map(MatriculaResponseDTO::fromModel)
                .collect(Collectors.toList());
    }
    
    private MatriculaModel buscarModelPorId(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new MatriculaNaoEncontradaException(id));
    }

    public List<MatriculaResponseDTO> listarTodas() {
        return matriculaRepository.findAll().stream()
                .map(MatriculaResponseDTO::fromModel)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void excluirMatricula(Long id) {
        MatriculaModel matricula = buscarModelPorId(id);
        matriculaRepository.delete(matricula);
    }
}