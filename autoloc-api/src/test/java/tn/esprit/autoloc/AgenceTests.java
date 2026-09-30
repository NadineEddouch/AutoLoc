package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AgenceTests {
    @Autowired
    private AgenceRepositoryMock agenceRepository;
    @Test
    void addAgence() {
        // Q7 : créer l'agence
        Agence agence = new Agence();
        agence.setNom("Agence ariana");
        agence.setVille("Tunis");
        agence.setAdresse("1 Rue Hedi");
        agence.setTelephone("71585874");

        // Q7 : les deux véhicules
        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("785414TU96");
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setCategorie(CategorieVehicule.SUV);
        v1.setTarifJournalier(new java.math.BigDecimal("100"));
        v1.setStatut(StatutVehicule.MAINTENANCE);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("785414TU95");
        v2.setMarque("Toyota");
        v2.setModele("Yaris");
        v2.setCategorie(CategorieVehicule.UTILITAIRE);
        v2.setTarifJournalier(new java.math.BigDecimal("80"));
        v2.setStatut(StatutVehicule.DISPONIBLE);

        // Important : initialiser la liste et relier les DEUX côtés
        agence.setVehicules(new java.util.ArrayList<>());
        v1.setAgence(agence);
        v2.setAgence(agence);
        agence.getVehicules().add(v1);
        agence.getVehicules().add(v2);

        // Q8 : un seul save sur l'agence, le cascade PERSIST enregistre les véhicules
        agenceRepository.save(agence);
    }

    // Q11 : méthode loadAgence
    @Test
    void loadAgence() {
        // Q12 : récupérer la liste de toutes les agences via findAll
        Iterable<Agence> agences = agenceRepository.findAll();

        // Q13 : parcourir et construire le résultat avec StringBuilder
        StringBuilder result = new StringBuilder();
        for (Agence agence : agences) {
            result.append("Agence ID: ").append(agence.getIdAgence())
                  .append(", Nom: ").append(agence.getNom())
                  .append(", Nombre de véhicules: ").append(agence.getVehicules() != null ? agence.getVehicules().size() : 0).append("\n");
            if (agence.getVehicules() != null) {
                for (Vehicule vehicule : agence.getVehicules()) {
                    result.append("  - Véhicule ID: ").append(vehicule.getIdVehicule())
                          .append(", Immatriculation: ").append(vehicule.getImmatriculation()).append("\n");
                }
            }
        }

        // Q14 : assertion qui échoue toujours pour afficher le résultat
        org.junit.jupiter.api.Assertions.fail(result.toString());
    }
}

// Q5 : interface dans le même fichier
interface AgenceRepositoryMock extends CrudRepository<Agence,Integer> {}


