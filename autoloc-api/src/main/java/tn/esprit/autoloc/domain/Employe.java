package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmploye;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RoleEmploye role;

    // Q16 : Relation Employe-Agence (ManyToOne)
    // Chap 9: Question 10 - gestion symétrique de la relation
    // Chap 9: Question 13 - @JsonIgnore pour éviter la référence circulaire
    @ManyToOne
    @JsonIgnore
    private Agence agence;

    // Chap 9: Question 10 - méthode helper pour définir l'agence de manière symétrique
    public void setAgence(Agence agence) {
        this.agence = agence;
        if (agence != null && !agence.getEmployes().contains(this)) {
            agence.getEmployes().add(this);
        }
    }
}
