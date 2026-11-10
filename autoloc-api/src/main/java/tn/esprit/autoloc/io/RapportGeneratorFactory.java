package tn.esprit.autoloc.io;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.service.IRapportGenerator;

// Chap 8: Question 14 - Factory pour créer les générateurs de rapports
@Component
public class RapportGeneratorFactory {

    private final ConsoleRapportDecorator consoleRapportDecorator;

    public RapportGeneratorFactory(ConsoleRapportDecorator consoleRapportDecorator) {
        this.consoleRapportDecorator = consoleRapportDecorator;
    }

    public IRapportGenerator createRapportGenerator() {
        // La factory retourne le décorateur (qui contient déjà le générateur basique)
        return consoleRapportDecorator;
    }
}
