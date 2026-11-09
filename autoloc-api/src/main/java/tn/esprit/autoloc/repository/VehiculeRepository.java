package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

// Chap 6 : question 25 - méthode pour trouver les véhicules par agence avant suppression
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    List<Vehicule> findByAgenceIdAgence(Long idAgence);
}
