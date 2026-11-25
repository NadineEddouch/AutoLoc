package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;
import tn.esprit.autoloc.web.dto.ReservationDTO;
import tn.esprit.autoloc.web.dto.ReservationMapper;

import java.util.List;

@Tag(name = "Reservations", description = "Gestion des réservations")
@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @GetMapping
    public List<ReservationDTO> findAll() { return reservationMapper.toDtoList(reservationService.findAll()); }
    @GetMapping("/{id}")
    public ReservationDTO findById(@PathVariable Long id) { return reservationMapper.toDto(reservationService.findById(id)); }
    @PostMapping
    public ReservationDTO create(@RequestBody ReservationDTO dto) { return reservationMapper.toDto(reservationService.create(reservationMapper.toEntity(dto))); }
    @PutMapping("/{id}")
    public ReservationDTO update(@PathVariable Long id, @RequestBody ReservationDTO dto) { return reservationMapper.toDto(reservationService.update(id, reservationMapper.toEntity(dto))); }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { reservationService.deleteById(id); }
}
