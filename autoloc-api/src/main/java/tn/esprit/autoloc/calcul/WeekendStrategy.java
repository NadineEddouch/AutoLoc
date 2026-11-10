package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

// Chap 8: Question 16 - Strategy pour l'offre WEEKEND (réduction 20%)
@Component
public class WeekendStrategy implements TarifStrategy {

    @Override
    public double calculer(double tarifJournalier, int nombreJours) {
        return tarifJournalier * 0.8 * nombreJours;
    }
}
