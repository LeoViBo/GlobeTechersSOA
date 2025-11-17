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

import com.globetechers.soa.dto.TrilhaRequestDTO;
import com.globetechers.soa.dto.TrilhaResponseDTO;
import com.globetechers.soa.service.TrilhaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trilhas")
public class TrilhaController {

    private final TrilhaService trilhaService;

    public TrilhaController(TrilhaService trilhaService) {
        this.trilhaService = trilhaService;
    }

    @PostMapping
    public ResponseEntity<TrilhaResponseDTO> criarTrilha(@Valid @RequestBody TrilhaRequestDTO dto) {
        TrilhaResponseDTO novaTrilha = trilhaService.criarTrilha(dto);
        return new ResponseEntity<>(novaTrilha, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrilhaResponseDTO>> listarTodas() {
        List<TrilhaResponseDTO> trilhas = trilhaService.listarTodas();
        return ResponseEntity.ok(trilhas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaResponseDTO> buscarPorId(@PathVariable Long id) {
        TrilhaResponseDTO trilha = trilhaService.buscarPorId(id);
        return ResponseEntity.ok(trilha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaResponseDTO> atualizarTrilha(@PathVariable Long id, 
                                                               @Valid @RequestBody TrilhaRequestDTO dto) {
        TrilhaResponseDTO trilhaAtualizada = trilhaService.atualizarTrilha(id, dto);
        return ResponseEntity.ok(trilhaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTrilha(@PathVariable Long id) {
        trilhaService.excluirTrilha(id);
        return ResponseEntity.noContent().build();
    }
}