package tn.esprit.autoloc.service;

import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;

import java.util.List;

// Chap 5: Question 5
// Chap 5: Question 16 - hérite de IGenericService
// Chap 8: Question 8 - méthode simuler
// Chap 9: Question 12 - méthode ajouterEmployes
public interface IAgenceService extends IGenericService<Agence, Long> {
    boolean hasVehicules(Long id);
    String simuler(Long agenceId, int nombreJours, VehiculeOffre offre);
    Agence ajouterEmployes(Long agenceId, List<Employe> nouveauxEmployes);
}
