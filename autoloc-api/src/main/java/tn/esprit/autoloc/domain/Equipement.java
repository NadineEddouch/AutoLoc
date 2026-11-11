package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "equipement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    @Column(nullable = false, unique = true, length = 100)
    private String libelle;

    // Q21 : Relation Equipement-Vehicule (ManyToMany)
    // Chap 9: Question 7 - @JsonIgnore pour éviter la référence circulaire
    @ManyToMany(mappedBy = "equipements")
    @JsonIgnore
    private List<Vehicule> vehicules;
}
