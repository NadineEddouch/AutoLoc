package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "contrat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    @Column(nullable = false)
    private LocalDate dateSignature;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(nullable = false)
    private Boolean valide;

    // Q18 : Relation Contrat-Paiement (EAGER)
    @OneToMany(mappedBy = "contrat", fetch = FetchType.EAGER)
    private List<Paiement> paiements;

    // Q20 : Relation Contrat-Reservation (OneToOne)
    @OneToOne(mappedBy = "contrat")
    private Reservation reservation;
}
