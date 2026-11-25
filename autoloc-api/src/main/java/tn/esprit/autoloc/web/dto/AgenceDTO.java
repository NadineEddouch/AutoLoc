package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgenceDTO {
    private Long idAgence;
    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nom;
    @NotBlank(message = "La ville est obligatoire")
    @Size(min = 2, max = 50, message = "La ville doit contenir entre 2 et 50 caractères")
    private String ville;
    @NotBlank(message = "L'adresse est obligatoire")
    @Size(min = 5, max = 200, message = "L'adresse doit contenir entre 5 et 200 caractères")
    private String adresse;
    @NotBlank(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "^[0-9]{8}$", message = "Le téléphone doit contenir 8 chiffres")
    private String telephone;
    @Min(value = 0, message = "L'effectif ne peut pas être négatif")
    private Integer effectif;
}
