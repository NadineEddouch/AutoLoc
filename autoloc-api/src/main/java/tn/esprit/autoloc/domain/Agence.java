package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 50)
    private String ville;

    @Column(nullable = false, length = 200)
    private String adresse;

    @Column(nullable = false, length = 20)
    private String telephone;

    // Chap 9: Question 11 - champ effectif pour le nombre d'employés
    @Column
    private Integer effectif = 0;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Vehicule> vehicules;

    // Q16 : Relation Agence-Employe (LAZY, pas de cascade)
    // Chap 9: Question 10 - gestion symétrique de la relation
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    private List<Employe> employes = new ArrayList<>();

    // Chap 9: Question 10 - méthode helper pour ajouter un employé de manière symétrique
    public void addEmploye(Employe employe) {
        employes.add(employe);
        employe.setAgence(this);
    }

    // Chap 9: Question 10 - méthode helper pour supprimer un employé de manière symétrique
    public void removeEmploye(Employe employe) {
        employes.remove(employe);
        employe.setAgence(null);
    }

    // Setter personnalisé pour initialiser la liste si null
    public void setEmployes(List<Employe> employes) {
        this.employes = employes != null ? employes : new ArrayList<>();
    }
}
