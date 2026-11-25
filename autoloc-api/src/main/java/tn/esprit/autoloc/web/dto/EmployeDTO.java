package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.autoloc.domain.RoleEmploye;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeDTO {
    private Long idEmploye;
    @NotBlank @Size(min=2, max=50) private String nom;
    @NotBlank @Size(min=2, max=50) private String prenom;
    @NotNull private RoleEmploye role;
}
