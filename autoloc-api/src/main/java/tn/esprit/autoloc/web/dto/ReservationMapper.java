package tn.esprit.autoloc.web.dto;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.domain.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationDTO toDto(Reservation reservation);
    Reservation toEntity(ReservationDTO reservationDTO);
    java.util.List<ReservationDTO> toDtoList(java.util.List<Reservation> reservations);
    java.util.List<Reservation> toEntityList(java.util.List<ReservationDTO> dtos);
}
