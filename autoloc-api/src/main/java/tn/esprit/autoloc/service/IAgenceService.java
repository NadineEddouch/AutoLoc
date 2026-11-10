package tn.esprit.autoloc.service;

import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;

// Chap 5: Question 5
// Chap 5: Question 16 - hérite de IGenericService
// Chap 8: Question 8 - méthode simuler
public interface IAgenceService extends IGenericService<Agence, Long> {
    boolean hasVehicules(Long id);
    String simuler(Long agenceId, int nombreJours, VehiculeOffre offre);
}
