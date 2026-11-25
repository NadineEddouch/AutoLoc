package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.service.IEquipementService;
import tn.esprit.autoloc.web.dto.EquipementDTO;
import tn.esprit.autoloc.web.dto.EquipementMapper;

import java.util.List;

// Atelier DTO - Question 9
@Tag(name = "Equipements", description = "Gestion des équipements")
@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {
    private final IEquipementService equipementService;
    private final EquipementMapper equipementMapper;

    @GetMapping
    public List<EquipementDTO> findAll() {
        return equipementMapper.toDtoList(equipementService.findAll());
    }

    @GetMapping("/{id}")
    public EquipementDTO findById(@Parameter(description = "Identifiant de l'équipement") @PathVariable Long id) {
        return equipementMapper.toDto(equipementService.findById(id));
    }

    @PostMapping
    public EquipementDTO create(@RequestBody EquipementDTO equipementDTO) {
        return equipementMapper.toDto(equipementService.create(equipementMapper.toEntity(equipementDTO)));
    }

    @PutMapping("/{id}")
    public EquipementDTO update(@PathVariable Long id, @RequestBody EquipementDTO equipementDTO) {
        return equipementMapper.toDto(equipementService.update(id, equipementMapper.toEntity(equipementDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
    }
}
