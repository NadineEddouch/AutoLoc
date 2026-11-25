package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDTO {
    private Long idMaintenance;
    @NotNull private LocalDate dateDebut;
    @NotNull private LocalDate dateFin;
    @NotBlank @Size(min=5, max=500) private String description;
}
