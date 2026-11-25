package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IEmployeService;
import tn.esprit.autoloc.web.dto.EmployeDTO;
import tn.esprit.autoloc.web.dto.EmployeMapper;

import java.util.List;

@Tag(name = "Employes", description = "Gestion des employés")
@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {
    private final IEmployeService employeService;
    private final EmployeMapper employeMapper;

    @GetMapping
    public List<EmployeDTO> findAll() { return employeMapper.toDtoList(employeService.findAll()); }
    @GetMapping("/{id}")
    public EmployeDTO findById(@PathVariable Long id) { return employeMapper.toDto(employeService.findById(id)); }
    @PostMapping
    public EmployeDTO create(@RequestBody EmployeDTO dto) { return employeMapper.toDto(employeService.create(employeMapper.toEntity(dto))); }
    @PutMapping("/{id}")
    public EmployeDTO update(@PathVariable Long id, @RequestBody EmployeDTO dto) { return employeMapper.toDto(employeService.update(id, employeMapper.toEntity(dto))); }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { employeService.deleteById(id); }
}
