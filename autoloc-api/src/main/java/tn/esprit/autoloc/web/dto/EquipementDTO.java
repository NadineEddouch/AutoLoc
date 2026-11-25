package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipementDTO {
    private Long idEquipement;
    @NotBlank @Size(min=2, max=100) private String libelle;
}
