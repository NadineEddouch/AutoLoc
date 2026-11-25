package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;
import tn.esprit.autoloc.web.dto.MaintenanceDTO;
import tn.esprit.autoloc.web.dto.MaintenanceMapper;

import java.util.List;

@Tag(name = "Maintenances", description = "Gestion des maintenances")
@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {
    private final IMaintenanceService maintenanceService;
    private final MaintenanceMapper maintenanceMapper;

    @GetMapping
    public List<MaintenanceDTO> findAll() { return maintenanceMapper.toDtoList(maintenanceService.findAll()); }
    @GetMapping("/{id}")
    public MaintenanceDTO findById(@PathVariable Long id) { return maintenanceMapper.toDto(maintenanceService.findById(id)); }
    @PostMapping
    public MaintenanceDTO create(@RequestBody MaintenanceDTO dto) { return maintenanceMapper.toDto(maintenanceService.create(maintenanceMapper.toEntity(dto))); }
    @PutMapping("/{id}")
    public MaintenanceDTO update(@PathVariable Long id, @RequestBody MaintenanceDTO dto) { return maintenanceMapper.toDto(maintenanceService.update(id, maintenanceMapper.toEntity(dto))); }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { maintenanceService.deleteById(id); }
}
