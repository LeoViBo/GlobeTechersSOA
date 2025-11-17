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

import com.globetechers.soa.dto.CompetenciaRequestDTO;
import com.globetechers.soa.dto.CompetenciaResponseDTO;
import com.globetechers.soa.service.CompetenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/competencias")
public class CompetenciaController {

    private final CompetenciaService competenciaService;

    public CompetenciaController(CompetenciaService competenciaService) {
        this.competenciaService = competenciaService;
    }

    @PostMapping
    public ResponseEntity<CompetenciaResponseDTO> criarCompetencia(@Valid @RequestBody CompetenciaRequestDTO dto) {
        CompetenciaResponseDTO novaCompetencia = competenciaService.criarCompetencia(dto);
        return new ResponseEntity<>(novaCompetencia, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompetenciaResponseDTO>> listarTodas() {
        List<CompetenciaResponseDTO> competencias = competenciaService.listarTodas();
        return ResponseEntity.ok(competencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenciaResponseDTO> buscarPorId(@PathVariable Long id) {
        CompetenciaResponseDTO competencia = competenciaService.buscarPorId(id);
        return ResponseEntity.ok(competencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetenciaResponseDTO> atualizarCompetencia(@PathVariable Long id, 
                                                               @Valid @RequestBody CompetenciaRequestDTO dto) {
        CompetenciaResponseDTO competenciaAtualizada = competenciaService.atualizarCompetencia(id, dto);
        return ResponseEntity.ok(competenciaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCompetencia(@PathVariable Long id) {
        competenciaService.excluirCompetencia(id);
        return ResponseEntity.noContent().build();
    }
}