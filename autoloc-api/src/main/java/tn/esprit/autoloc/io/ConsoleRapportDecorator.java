package tn.esprit.autoloc.io;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IRapportGenerator;

// Chap 8: Question 14 - Decorator qui affiche l'en-tête sur la console
@Component
public class ConsoleRapportDecorator implements IRapportGenerator {

    private final IRapportGenerator rapportGenerator;

    public ConsoleRapportDecorator(IRapportGenerator rapportGenerator) {
        this.rapportGenerator = rapportGenerator;
    }

    @Override
    public String generer(Agence agence, int nombreJours, VehiculeOffre offre) {
        // Afficher l'en-tête sur la console
        System.out.println("=== RAPPORT CONSOLE ===");
        System.out.println("ID: " + agence.getIdAgence());
        System.out.println("Nom: " + agence.getNom());
        System.out.println("Téléphone: " + agence.getTelephone());
        System.out.println("------------------------");

        // Déléguer la génération du rapport au générateur décoré
        String rapport = rapportGenerator.generer(agence, nombreJours, offre);

        // Retourner le total (selon l'énoncé)
        return rapport;
    }
}
