package tn.esprit.autoloc.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculeDTO {
    private Long idVehicule;
    @NotBlank(message = "L'immatriculation est obligatoire")
    @Pattern(regexp = "^[0-9]{1,4}\\s?[A-Z]{2}\\s?[0-9]{1,5}$", message = "Format d'immatriculation invalide (ex: 123 TN 1234)")
    private String immatriculation;
    @NotBlank(message = "La marque est obligatoire")
    @Size(min = 2, max = 50, message = "La marque doit contenir entre 2 et 50 caractères")
    private String marque;
    @NotBlank(message = "Le modèle est obligatoire")
    @Size(min = 2, max = 50, message = "Le modèle doit contenir entre 2 et 50 caractères")
    private String modele;
    @NotNull(message = "La catégorie est obligatoire")
    private CategorieVehicule categorie;
    @NotNull(message = "Le tarif journalier est obligatoire")
    @DecimalMin(value = "0.01", message = "Le tarif journalier doit être supérieur à 0")
    private BigDecimal tarifJournalier;
    @NotNull(message = "Le statut est obligatoire")
    private StatutVehicule statut;
}
