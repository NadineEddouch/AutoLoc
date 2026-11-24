package tn.esprit.autoloc.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IRapportGenerator;

// Chap 8: Question 14 - Decorator qui affiche l'en-tête sur la console
@Component
public class ConsoleRapportDecorator implements IRapportGenerator {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleRapportDecorator.class);
    private final IRapportGenerator rapportGenerator;

    public ConsoleRapportDecorator(IRapportGenerator rapportGenerator) {
        this.rapportGenerator = rapportGenerator;
    }

    @Override
    public String generer(Agence agence, int nombreJours, VehiculeOffre offre) {
        // Afficher l'en-tête sur la console
        logger.info("=== RAPPORT CONSOLE ===");
        logger.info("ID: {}", agence.getIdAgence());
        logger.info("Nom: {}", agence.getNom());
        logger.info("Téléphone: {}", agence.getTelephone());
        logger.info("------------------------");

        // Déléguer la génération du rapport au générateur décoré et retourner directement
        return rapportGenerator.generer(agence, nombreJours, offre);
    }
}
