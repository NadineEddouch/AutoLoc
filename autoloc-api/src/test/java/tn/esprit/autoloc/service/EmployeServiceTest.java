package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.RoleEmploye;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeServiceTest {

    @Autowired
    private IEmployeService employeService;

    @Test
    void testCreateEmploye() {
        Employe employe = new Employe();
        employe.setNom("Employe Test");
        employe.setPrenom("Test");
        employe.setRole(RoleEmploye.AGENT);

        Employe created = employeService.create(employe);
        assertNotNull(created);
        assertNotNull(created.getIdEmploye());
        assertEquals("Employe Test", created.getNom());
    }

    @Test
    void testFindById() {
        Employe employe = new Employe();
        employe.setNom("Employe Find Test");
        employe.setPrenom("Test");
        employe.setRole(RoleEmploye.MANAGER);

        Employe created = employeService.create(employe);
        Employe found = employeService.findById(created.getIdEmploye());

        assertNotNull(found);
        assertEquals(created.getIdEmploye(), found.getIdEmploye());
        assertEquals("Employe Find Test", found.getNom());
    }

    @Test
    void testFindAll() {
        assertNotNull(employeService.findAll());
    }

    @Test
    void testUpdateEmploye() {
        Employe employe = new Employe();
        employe.setNom("Employe Update Test");
        employe.setPrenom("Test");
        employe.setRole(RoleEmploye.AGENT);

        Employe created = employeService.create(employe);
        created.setNom("Employe Updated");
        created.setRole(RoleEmploye.MANAGER);

        Employe updated = employeService.update(created.getIdEmploye(), created);
        assertEquals("Employe Updated", updated.getNom());
        assertEquals(RoleEmploye.MANAGER, updated.getRole());
    }

    @Test
    void testDeleteById() {
        Employe employe = new Employe();
        employe.setNom("Employe Delete Test");
        employe.setPrenom("Test");
        employe.setRole(RoleEmploye.AGENT);

        Employe created = employeService.create(employe);
        employeService.deleteById(created.getIdEmploye());

        assertThrows(Exception.class, () -> employeService.findById(created.getIdEmploye()));
    }

    @Test
    void testCreateEmployeAgent() {
        Employe employe = new Employe();
        employe.setNom("Agent Test");
        employe.setPrenom("Test");
        employe.setRole(RoleEmploye.AGENT);

        Employe created = employeService.create(employe);
        assertEquals(RoleEmploye.AGENT, created.getRole());
    }

    @Test
    void testCreateEmployeManager() {
        Employe employe = new Employe();
        employe.setNom("Manager Test");
        employe.setPrenom("Test");
        employe.setRole(RoleEmploye.MANAGER);

        Employe created = employeService.create(employe);
        assertEquals(RoleEmploye.MANAGER, created.getRole());
    }

    @Test
    void testUpdateEmployePrenom() {
        Employe employe = new Employe();
        employe.setNom("Employe Prenom Test");
        employe.setPrenom("OldPrenom");
        employe.setRole(RoleEmploye.AGENT);

        Employe created = employeService.create(employe);
        created.setPrenom("NewPrenom");

        Employe updated = employeService.update(created.getIdEmploye(), created);
        assertEquals("NewPrenom", updated.getPrenom());
    }

    @Test
    void testUpdateEmployeRoleAgentToManager() {
        Employe employe = new Employe();
        employe.setNom("Employe Role Test");
        employe.setPrenom("Test");
        employe.setRole(RoleEmploye.AGENT);

        Employe created = employeService.create(employe);
        created.setRole(RoleEmploye.MANAGER);

        Employe updated = employeService.update(created.getIdEmploye(), created);
        assertEquals(RoleEmploye.MANAGER, updated.getRole());
    }

    @Test
    void testUpdateEmployeRoleManagerToAgent() {
        Employe employe = new Employe();
        employe.setNom("Employe Role Test 2");
        employe.setPrenom("Test");
        employe.setRole(RoleEmploye.MANAGER);

        Employe created = employeService.create(employe);
        created.setRole(RoleEmploye.AGENT);

        Employe updated = employeService.update(created.getIdEmploye(), created);
        assertEquals(RoleEmploye.AGENT, updated.getRole());
    }
}
