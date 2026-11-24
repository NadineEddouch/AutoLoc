package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.domain.Agence;

import static org.junit.jupiter.api.Assertions.*;

// Atelier 10 - Question 13 : Tests unitaires pour AgenceService
@SpringBootTest
class AgenceServiceTest {

    @Autowired
    private IAgenceService agenceService;

    @Test
    void testCreateAgence() {
        Agence agence = new Agence();
        agence.setNom("Agence Test");
        agence.setVille("Tunis");
        agence.setAdresse("1 Rue Test");
        agence.setTelephone("12345678");
        agence.setEffectif(10);

        Agence created = agenceService.create(agence);
        assertNotNull(created);
        assertNotNull(created.getIdAgence());
        assertEquals("Agence Test", created.getNom());
    }

    @Test
    void testFindById() {
        Agence agence = new Agence();
        agence.setNom("Agence Find Test");
        agence.setVille("Sfax");
        agence.setAdresse("2 Rue Find");
        agence.setTelephone("87654321");
        agence.setEffectif(5);

        Agence created = agenceService.create(agence);
        Agence found = agenceService.findById(created.getIdAgence());

        assertNotNull(found);
        assertEquals(created.getIdAgence(), found.getIdAgence());
        assertEquals("Agence Find Test", found.getNom());
    }

    @Test
    void testFindAll() {
        assertNotNull(agenceService.findAll());
    }

    @Test
    void testUpdateAgence() {
        Agence agence = new Agence();
        agence.setNom("Agence Update Test");
        agence.setVille("Bizerte");
        agence.setAdresse("3 Rue Update");
        agence.setTelephone("11111111");
        agence.setEffectif(8);

        Agence created = agenceService.create(agence);
        created.setNom("Agence Updated");

        Agence updated = agenceService.update(created.getIdAgence(), created);
        assertEquals("Agence Updated", updated.getNom());
        assertEquals(8, updated.getEffectif());
    }

    @Test
    void testDeleteById() {
        Agence agence = new Agence();
        agence.setNom("Agence Delete Test");
        agence.setVille("Gabes");
        agence.setAdresse("4 Rue Delete");
        agence.setTelephone("22222222");
        agence.setEffectif(3);

        Agence created = agenceService.create(agence);
        agenceService.deleteById(created.getIdAgence());

        assertThrows(Exception.class, () -> agenceService.findById(created.getIdAgence()));
    }

    @Test
    void testCreateAgenceWithZeroEffectif() {
        Agence agence = new Agence();
        agence.setNom("Agence Zero Effectif");
        agence.setVille("Monastir");
        agence.setAdresse("5 Rue Zero");
        agence.setTelephone("33333333");
        agence.setEffectif(0);

        Agence created = agenceService.create(agence);
        assertNotNull(created);
        assertEquals(0, created.getEffectif());
    }

    @Test
    void testCreateAgenceWithLargeEffectif() {
        Agence agence = new Agence();
        agence.setNom("Agence Large Effectif");
        agence.setVille("Mahdia");
        agence.setAdresse("6 Rue Large");
        agence.setTelephone("44444444");
        agence.setEffectif(100);

        Agence created = agenceService.create(agence);
        assertNotNull(created);
        assertEquals(100, created.getEffectif());
    }

    @Test
    void testUpdateAgenceCity() {
        Agence agence = new Agence();
        agence.setNom("Agence Ville Test");
        agence.setVille("Sousse");
        agence.setAdresse("7 Rue Ville");
        agence.setTelephone("55555555");
        agence.setEffectif(7);

        Agence created = agenceService.create(agence);
        created.setVille("Kairouan");

        Agence updated = agenceService.update(created.getIdAgence(), created);
        assertEquals("Kairouan", updated.getVille());
    }

    @Test
    void testUpdateAgenceTelephone() {
        Agence agence = new Agence();
        agence.setNom("Agence Tel Test");
        agence.setVille("Jendouba");
        agence.setAdresse("8 Rue Tel");
        agence.setTelephone("66666666");
        agence.setEffectif(4);

        Agence created = agenceService.create(agence);
        created.setTelephone("77777777");

        Agence updated = agenceService.update(created.getIdAgence(), created);
        assertEquals("77777777", updated.getTelephone());
    }
}
