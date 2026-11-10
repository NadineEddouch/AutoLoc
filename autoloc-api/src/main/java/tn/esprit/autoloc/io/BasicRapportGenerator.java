package tn.esprit.autoloc.io;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IRapportGenerator;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

// Chap 8: Question 14 - Implémentation basique du générateur de rapport
@Component
public class BasicRapportGenerator implements IRapportGenerator {

    private final IVehiculeService vehiculeService;

    public BasicRapportGenerator(IVehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @Override
    public String generer(Agence agence, int nombreJours, VehiculeOffre offre) {
        // En-tête du rapport
        StringBuilder rapport = new StringBuilder();
        rapport.append("=== RAPPORT D'AGENCE ===\n");
        rapport.append("ID: ").append(agence.getIdAgence()).append("\n");
        rapport.append("Nom: ").append(agence.getNom()).append("\n");
        rapport.append("Téléphone: ").append(agence.getTelephone()).append("\n");
        rapport.append("------------------------\n");

        // Calcul du total des tarifs des véhicules
        List<Vehicule> vehicules = agence.getVehicules();
        double total = 0;

        if (vehicules != null && !vehicules.isEmpty()) {
            rapport.append("Véhicules:\n");
            for (Vehicule vehicule : vehicules) {
                double tarif = vehiculeService.calculTarif(vehicule.getIdVehicule(), nombreJours, offre);
                total += tarif;
                rapport.append("  - ").append(vehicule.getMarque()).append(" ")
                      .append(vehicule.getModele()).append(": ").append(tarif).append(" DT\n");
            }
        } else {
            rapport.append("Aucun véhicule\n");
        }

        rapport.append("------------------------\n");
        rapport.append("TOTAL: ").append(total).append(" DT\n");
        rapport.append("Offre: ").append(offre).append("\n");
        rapport.append("Nombre de jours: ").append(nombreJours).append("\n");

        return rapport.toString();
    }
}
