package tn.esprit.autoloc.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgenceDTO {
    private Long idAgence;
    private String nom;
    private String ville;
    private String adresse;
    private String telephone;
    private Integer effectif;
}
