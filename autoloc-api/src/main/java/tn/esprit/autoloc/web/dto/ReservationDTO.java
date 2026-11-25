package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.autoloc.domain.StatutReservation;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Long idReservation;
    @NotNull private LocalDate dateDebut;
    @NotNull private LocalDate dateFin;
    @NotNull private StatutReservation statut;
}
