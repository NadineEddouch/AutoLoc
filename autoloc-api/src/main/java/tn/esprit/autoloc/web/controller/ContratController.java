package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;
import tn.esprit.autoloc.web.dto.ContratDTO;
import tn.esprit.autoloc.web.dto.ContratMapper;

import java.util.List;

@Tag(name = "Contrats", description = "Gestion des contrats de location")
@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {
    private final IContratService contratService;
    private final ContratMapper contratMapper;

    @GetMapping
    public List<ContratDTO> findAll() { return contratMapper.toDtoList(contratService.findAll()); }
    @GetMapping("/{id}")
    public ContratDTO findById(@PathVariable Long id) { return contratMapper.toDto(contratService.findById(id)); }
    @PostMapping
    public ContratDTO create(@RequestBody ContratDTO dto) { return contratMapper.toDto(contratService.create(contratMapper.toEntity(dto))); }
    @PutMapping("/{id}")
    public ContratDTO update(@PathVariable Long id, @RequestBody ContratDTO dto) { return contratMapper.toDto(contratService.update(id, contratMapper.toEntity(dto))); }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { contratService.deleteById(id); }
}
