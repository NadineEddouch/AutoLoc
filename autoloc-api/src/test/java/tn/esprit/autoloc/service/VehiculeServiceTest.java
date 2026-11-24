package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehiculeServiceTest {

    @Autowired
    private IVehiculeService vehiculeService;

    @Test
    void testCreateVehicule() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("TEST" + UUID.randomUUID().toString().substring(0, 5));
        vehicule.setMarque("Toyota");
        vehicule.setModele("Yaris");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("50"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule created = vehiculeService.create(vehicule);
        assertNotNull(created);
        assertNotNull(created.getIdVehicule());
        assertEquals("Toyota", created.getMarque());
    }

    @Test
    void testFindById() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("FIND" + UUID.randomUUID().toString().substring(0, 5));
        vehicule.setMarque("Renault");
        vehicule.setModele("Clio");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("40"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule created = vehiculeService.create(vehicule);
        Vehicule found = vehiculeService.findById(created.getIdVehicule());

        assertNotNull(found);
        assertEquals(created.getIdVehicule(), found.getIdVehicule());
    }

    @Test
    void testFindAll() {
        assertNotNull(vehiculeService.findAll());
    }

    @Test
    void testUpdateVehicule() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("UPD" + UUID.randomUUID().toString().substring(0, 5));
        vehicule.setMarque("Peugeot");
        vehicule.setModele("208");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("45"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule created = vehiculeService.create(vehicule);
        created.setMarque("Peugeot Updated");

        Vehicule updated = vehiculeService.update(created.getIdVehicule(), created);
        assertEquals("Peugeot Updated", updated.getMarque());
    }

    @Test
    void testDeleteById() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("DEL" + UUID.randomUUID().toString().substring(0, 5));
        vehicule.setMarque("Citroen");
        vehicule.setModele("C3");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("35"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule created = vehiculeService.create(vehicule);
        vehiculeService.deleteById(created.getIdVehicule());

        assertThrows(Exception.class, () -> vehiculeService.findById(created.getIdVehicule()));
    }

    @Test
    void testCreateVehiculeSUV() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("SUV" + UUID.randomUUID().toString().substring(0, 5));
        vehicule.setMarque("BMW");
        vehicule.setModele("X5");
        vehicule.setCategorie(CategorieVehicule.SUV);
        vehicule.setTarifJournalier(new BigDecimal("120"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule created = vehiculeService.create(vehicule);
        assertEquals(CategorieVehicule.SUV, created.getCategorie());
    }

    @Test
    void testCreateVehiculeBerline() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("BER" + UUID.randomUUID().toString().substring(0, 5));
        vehicule.setMarque("Mercedes");
        vehicule.setModele("Classe E");
        vehicule.setCategorie(CategorieVehicule.BERLINE);
        vehicule.setTarifJournalier(new BigDecimal("150"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule created = vehiculeService.create(vehicule);
        assertEquals(CategorieVehicule.BERLINE, created.getCategorie());
    }

    @Test
    void testCreateVehiculeUtilitaire() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("UTI" + UUID.randomUUID().toString().substring(0, 5));
        vehicule.setMarque("Renault");
        vehicule.setModele("Kangoo");
        vehicule.setCategorie(CategorieVehicule.UTILITAIRE);
        vehicule.setTarifJournalier(new BigDecimal("60"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule created = vehiculeService.create(vehicule);
        assertEquals(CategorieVehicule.UTILITAIRE, created.getCategorie());
    }

    @Test
    void testUpdateVehiculeTarif() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("TAR" + UUID.randomUUID().toString().substring(0, 5));
        vehicule.setMarque("Ford");
        vehicule.setModele("Fiesta");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("55"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule created = vehiculeService.create(vehicule);
        created.setTarifJournalier(new BigDecimal("65"));

        Vehicule updated = vehiculeService.update(created.getIdVehicule(), created);
        assertEquals(new BigDecimal("65"), updated.getTarifJournalier());
    }

    @Test
    void testUpdateVehiculeStatut() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("STA" + UUID.randomUUID().toString().substring(0, 5));
        vehicule.setMarque("Volkswagen");
        vehicule.setModele("Golf");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("50"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule created = vehiculeService.create(vehicule);
        created.setStatut(StatutVehicule.LOUE);

        Vehicule updated = vehiculeService.update(created.getIdVehicule(), created);
        assertEquals(StatutVehicule.LOUE, updated.getStatut());
    }

    @Test
    void testUpdateVehiculeModele() {
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("MOD" + UUID.randomUUID().toString().substring(0, 5));
        vehicule.setMarque("Seat");
        vehicule.setModele("Ibiza");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("48"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule created = vehiculeService.create(vehicule);
        created.setModele("Leon");

        Vehicule updated = vehiculeService.update(created.getIdVehicule(), created);
        assertEquals("Leon", updated.getModele());
    }
}
