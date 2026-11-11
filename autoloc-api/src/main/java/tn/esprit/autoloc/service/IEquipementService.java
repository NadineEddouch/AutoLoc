package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

// Chap 5: Question 20
// Chap 9: Question 9 - méthode pour désaffecter les véhicules avant suppression
public interface IEquipementService extends IGenericService<Equipement, Long> {
    void desaffecterVehicules(Long equipementId);
}
