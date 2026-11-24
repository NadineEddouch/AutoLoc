package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.domain.Maintenance;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MaintenanceServiceTest {

    @Autowired
    private IMaintenanceService maintenanceService;

    @Test
    void testFindAll() {
        assertNotNull(maintenanceService.findAll());
    }

    @Test
    void testCreateMaintenance() {
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        maintenance.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(1));
        maintenance.setDescription("Oil change");

        Maintenance created = maintenanceService.create(maintenance);
        assertNotNull(created);
        assertNotNull(created.getIdMaintenance());
        assertEquals("Oil change", created.getDescription());
    }

    @Test
    void testFindById() {
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        maintenance.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(2));
        maintenance.setDescription("Brake replacement");

        Maintenance created = maintenanceService.create(maintenance);
        Maintenance found = maintenanceService.findById(created.getIdMaintenance());

        assertNotNull(found);
        assertEquals(created.getIdMaintenance(), found.getIdMaintenance());
    }

    @Test
    void testUpdateMaintenance() {
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        maintenance.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(1));
        maintenance.setDescription("Tire rotation");

        Maintenance created = maintenanceService.create(maintenance);
        created.setDescription("Full service");

        Maintenance updated = maintenanceService.update(created.getIdMaintenance(), created);
        assertEquals("Full service", updated.getDescription());
    }

    @Test
    void testCreateMaintenanceWithLongDescription() {
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        maintenance.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(1));
        maintenance.setDescription("Complete engine service with oil change, filter replacement, and spark plug inspection");

        Maintenance created = maintenanceService.create(maintenance);
        assertNotNull(created);
        assertTrue(created.getDescription().contains("oil change"));
    }

    @Test
    void testUpdateMaintenanceDescription() {
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        maintenance.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(1));
        maintenance.setDescription("Initial description");

        Maintenance created = maintenanceService.create(maintenance);
        created.setDescription("Updated description");

        Maintenance updated = maintenanceService.update(created.getIdMaintenance(), created);
        assertEquals("Updated description", updated.getDescription());
    }

    @Test
    void testUpdateMaintenanceDates() {
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        maintenance.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(2));
        maintenance.setDescription("Periodic check");

        Maintenance created = maintenanceService.create(maintenance);
        LocalDate newDebut = LocalDate.now(ZoneId.systemDefault()).plusDays(7);
        LocalDate newFin = LocalDate.now(ZoneId.systemDefault()).plusDays(8);
        created.setDateDebut(newDebut);
        created.setDateFin(newFin);

        Maintenance updated = maintenanceService.update(created.getIdMaintenance(), created);
        assertEquals(newDebut, updated.getDateDebut());
        assertEquals(newFin, updated.getDateFin());
    }

    @Test
    void testCreateMaintenanceWithFutureDates() {
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(LocalDate.now(ZoneId.systemDefault()).plusDays(30));
        maintenance.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(31));
        maintenance.setDescription("Scheduled maintenance");

        Maintenance created = maintenanceService.create(maintenance);
        assertNotNull(created);
        assertTrue(created.getDateDebut().isAfter(LocalDate.now(ZoneId.systemDefault())));
    }

    @Test
    void testUpdateMaintenanceDateDebut() {
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        maintenance.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(1));
        maintenance.setDescription("Battery replacement");

        Maintenance created = maintenanceService.create(maintenance);
        LocalDate newDebut = LocalDate.now(ZoneId.systemDefault()).plusDays(3);
        created.setDateDebut(newDebut);

        Maintenance updated = maintenanceService.update(created.getIdMaintenance(), created);
        assertEquals(newDebut, updated.getDateDebut());
    }

    @Test
    void testUpdateMaintenanceDateFin() {
        Maintenance maintenance = new Maintenance();
        maintenance.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        maintenance.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(1));
        maintenance.setDescription("Suspension check");

        Maintenance created = maintenanceService.create(maintenance);
        LocalDate newFin = LocalDate.now(ZoneId.systemDefault()).plusDays(5);
        created.setDateFin(newFin);

        Maintenance updated = maintenanceService.update(created.getIdMaintenance(), created);
        assertEquals(newFin, updated.getDateFin());
    }
}
