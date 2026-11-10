package tn.esprit.autoloc.calcul;

// Chap 8: Question 16 - Interface Strategy pour le calcul de tarifs
public interface TarifStrategy {
    double calculer(double tarifJournalier, int nombreJours);
}
