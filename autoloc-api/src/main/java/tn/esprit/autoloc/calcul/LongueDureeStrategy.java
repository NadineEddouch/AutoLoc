package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;

// Chap 8: Question 16 - Strategy pour l'offre LONGUE_DUREE (réduction 25% + 5% si > 2000)
@Component
public class LongueDureeStrategy implements TarifStrategy {

    @Override
    public double calculer(double tarifJournalier, int nombreJours) {
        double total = tarifJournalier * 0.75 * nombreJours;
        // Réduction supplémentaire de 5% si le total dépasse 2000
        if (total > 2000) {
            total = total * 0.95;
        }
        return total;
    }
}
