package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaintenance;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(nullable = false, length = 500)
    private String description;

    // Q19 : Relation Maintenance-Vehicule (ManyToOne)
    @ManyToOne
    private Vehicule vehicule;

    // Méthode réservée pour une future implémentation de la date de maintenance
    public void setDateMaintenance(LocalDate now) {
        throw new UnsupportedOperationException("Méthode non implémentée");
    }
}
