package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AgenceTests {
    // Q2 : renommer l'attribut en basicAgenceRepository
    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    // Q3 : ajouter un attribut de type IAgenceRepository
    @Autowired
    private IAgenceRepository fullAgenceRepository;
    // Q6 : rendre addAgence privée et l'enlever des tests
    private void addAgence(CrudRepository<Agence, ?> repository) {
        // Q7 : créer l'agence
        Agence agence = new Agence();
        agence.setNom("Agence ariana");
        agence.setVille("Tunis");
        agence.setAdresse("1 Rue Hedi");
        agence.setTelephone("71585874");

        // Q7 : les deux véhicules
        Vehicule v1 = new Vehicule();
        // Q5 : ajouter timestamp à l'immatriculation pour éviter les duplications
        v1.setImmatriculation("785414TU96" + (int) System.currentTimeMillis());
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setCategorie(CategorieVehicule.SUV);
        v1.setTarifJournalier(new java.math.BigDecimal("100"));
        v1.setStatut(StatutVehicule.MAINTENANCE);

        Vehicule v2 = new Vehicule();
        // Q5 : ajouter timestamp à l'immatriculation pour éviter les duplications
        v2.setImmatriculation("785414TU95" + (int) System.currentTimeMillis());
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
        repository.save(agence);
    }

    // Q7 : méthode de test basicAddAgence
    @Test
    void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    // Q7 : méthode de test fullAddAgence
    @Test
    void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    // Q10 : rendre loadAgence privée avec paramètre et info sur type de dépôt
    private void loadAgence(CrudRepository<Agence, ?> repository, String repositoryType) {
        // Q12 : récupérer la liste de toutes les agences via findAll
        Iterable<Agence> agences = repository.findAll();

        // Q13 : parcourir et construire le résultat avec StringBuilder
        StringBuilder result = new StringBuilder();
        result.append("=== Type de dépôt: ").append(repositoryType).append(" ===\n");
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

    // Q10 : méthode de test basicLoadAgence
    @Test
    void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "Basic (CrudRepository)");
    }

    // Q10 : méthode de test fullLoadAgence
    @Test
    void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "Full (JpaRepository)");
    }

    // Q11 : méthode loadSortedAgences
    @Test
    void loadSortedAgences() {
        // Q12 : charger toutes les agences triées par id décroissants avec Sort
        StringBuilder stringBuilder = new StringBuilder();

        Sort sort = Sort.by(Sort.Direction.DESC, "idAgence");

        Iterable<Agence> agences = fullAgenceRepository.findAll(sort);
        for (Agence e : agences) {
            stringBuilder.append("\n" + e.getIdAgence() + " | " + e.getNom() + "\n");
            stringBuilder.append("Vehicules Count : " + e.getVehicules().size() + "\n");
        }

        org.junit.jupiter.api.Assertions.fail(stringBuilder.toString());
    }

    // Q15 : méthode loadPagedAgences
    @Test
    void loadPagedAgences() {
        // Q16 : charger agences triées par id décroissant et paginer par lot de 2
        StringBuilder stringBuilder = new StringBuilder();

        int pageNumber = 0;
        int pageSize = 2;

        while (true) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "idAgence"));
            Page<Agence> page = fullAgenceRepository.findAll(pageable);

            if (!page.hasContent()) {
                break;
            }

            // Q17 : afficher nombre total de pages, page en cours et infos agences
            stringBuilder.append("\n=== Page " + (pageNumber + 1) + " / " + page.getTotalPages() + " ===\n");
            stringBuilder.append("Total éléments: " + page.getTotalElements() + "\n");
            for (Agence e : page.getContent()) {
                stringBuilder.append(e.getIdAgence() + " | " + e.getNom() + "\n");
            }

            pageNumber++;
        }

        org.junit.jupiter.api.Assertions.fail(stringBuilder.toString());
    }
}

// Q5 : interface dans le même fichier
interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {}


