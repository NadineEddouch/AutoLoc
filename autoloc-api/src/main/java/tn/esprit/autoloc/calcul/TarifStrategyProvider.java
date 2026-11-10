package tn.esprit.autoloc.calcul;

import org.springframework.stereotype.Component;
import java.util.Map;

// Chap 8: Question 16 - Provider de stratégies utilisant une Map et Spring IoC
@Component
public class TarifStrategyProvider {

    private final Map<VehiculeOffre, TarifStrategy> strategyMap;

    // Spring IoC injecte automatiquement tous les beans de type TarifStrategy
    // Nous devons les mapper manuellement aux offres
    public TarifStrategyProvider(StandardStrategy standardStrategy,
                                  WeekendStrategy weekendStrategy,
                                  LongueDureeStrategy longueDureeStrategy) {
        this.strategyMap = Map.of(
            VehiculeOffre.STANDARD, standardStrategy,
            VehiculeOffre.WEEKEND, weekendStrategy,
            VehiculeOffre.LONGUE_DUREE, longueDureeStrategy
        );
    }

    public TarifStrategy getStrategy(VehiculeOffre offre) {
        return strategyMap.get(offre);
    }
}
