package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutReservation statut;

    // Q16 : Relation Reservation-Vehicule (ManyToOne)
    @ManyToOne
    private Vehicule vehicule;

    // Q16 : Relation Reservation-Client (ManyToOne)
    @ManyToOne
    private Client client;

    // Q20 : Relation Reservation-Contrat (OneToOne)
    @OneToOne
    private Contrat contrat;
}
