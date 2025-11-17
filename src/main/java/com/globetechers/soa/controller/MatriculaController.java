package com.globetechers.soa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globetechers.soa.dto.MatriculaRequestDTO;
import com.globetechers.soa.dto.MatriculaResponseDTO;
import com.globetechers.soa.service.MatriculaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> matricularUsuario(@Valid @RequestBody MatriculaRequestDTO dto) {
        MatriculaResponseDTO novaMatricula = matriculaService.matricularUsuario(dto);
        return new ResponseEntity<>(novaMatricula, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDTO>> listarTodas() {
        List<MatriculaResponseDTO> matriculas = matriculaService.listarTodas();
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponseDTO> buscarPorId(@PathVariable Long id) {
        MatriculaResponseDTO matricula = matriculaService.buscarPorId(id);
        return ResponseEntity.ok(matricula);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MatriculaResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<MatriculaResponseDTO> matriculas = matriculaService.listarMatriculasPorUsuario(usuarioId);
        return ResponseEntity.ok(matriculas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaResponseDTO> atualizarMatricula(@PathVariable Long id, 
                                                               @Valid @RequestBody MatriculaRequestDTO dto) {
        MatriculaResponseDTO matriculaAtualizada = matriculaService.atualizarMatricula(id, dto);
        return ResponseEntity.ok(matriculaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMatricula(@PathVariable Long id) {
        matriculaService.excluirMatricula(id);
        return ResponseEntity.noContent().build();
    }
}