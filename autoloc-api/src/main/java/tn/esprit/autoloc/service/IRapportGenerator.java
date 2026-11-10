package tn.esprit.autoloc.service;

import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;

// Chap 8: Question 11 - Interface pour la génération de rapports (SRP - Single Responsibility Principle)
public interface IRapportGenerator {
    String generer(Agence agence, int nombreJours, VehiculeOffre offre);
}
