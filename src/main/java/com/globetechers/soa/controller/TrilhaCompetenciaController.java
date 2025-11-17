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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globetechers.soa.dto.TrilhaCompetenciaRequestDTO;
import com.globetechers.soa.dto.TrilhaCompetenciaResponseDTO;
import com.globetechers.soa.service.TrilhaCompetenciaService;

import jakarta.validation.Valid;

class ResourceNotFoundException extends RuntimeException { public ResourceNotFoundException(String message) { super(message); } }
class BusinessException extends RuntimeException { public BusinessException(String message) { super(message); } }

@RestController
@RequestMapping("/api/trilhas-competencias")
public class TrilhaCompetenciaController {

    private final TrilhaCompetenciaService trilhaCompetenciaService;

    public TrilhaCompetenciaController(TrilhaCompetenciaService trilhaCompetenciaService) {
        this.trilhaCompetenciaService = trilhaCompetenciaService;
    }

    @PostMapping
    public ResponseEntity<TrilhaCompetenciaResponseDTO> create(@Valid @RequestBody TrilhaCompetenciaRequestDTO request) {
        try {
            TrilhaCompetenciaResponseDTO response = trilhaCompetenciaService.create(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrilhaCompetenciaResponseDTO> findById(@PathVariable Long id) {
        try {
            TrilhaCompetenciaResponseDTO response = trilhaCompetenciaService.findById(id);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TrilhaCompetenciaResponseDTO>> findAll() {
        List<TrilhaCompetenciaResponseDTO> response = trilhaCompetenciaService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = "trilhaId")
    public ResponseEntity<List<TrilhaCompetenciaResponseDTO>> findByTrilhaId(@RequestParam Long trilhaId) {
        List<TrilhaCompetenciaResponseDTO> response = trilhaCompetenciaService.findByTrilhaId(trilhaId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = "competenciaId")
    public ResponseEntity<List<TrilhaCompetenciaResponseDTO>> findByCompetenciaId(@RequestParam Long competenciaId) {
        List<TrilhaCompetenciaResponseDTO> response = trilhaCompetenciaService.findByCompetenciaId(competenciaId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrilhaCompetenciaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TrilhaCompetenciaRequestDTO request) {
        try {
            TrilhaCompetenciaResponseDTO response = trilhaCompetenciaService.update(id, request);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            trilhaCompetenciaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}