package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

// Chap 8: Question 16 - Strategy pour l'offre STANDARD (pas de réduction)
@Component
public class StandardStrategy implements TarifStrategy {

    @Override
    public double calculer(double tarifJournalier, int nombreJours) {
        return tarifJournalier * nombreJours;
    }
}
