package tn.esprit.autoloc.service;

import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Vehicule;

// Chap 5: Question 20
// Chap 8: Question 6-7 - méthode calculTarif avec paramètre offre
public interface IVehiculeService extends IGenericService<Vehicule, Long> {
    double calculTarif(Long vehiculeId, int nombreJours, VehiculeOffre offre);
}
