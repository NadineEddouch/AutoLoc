package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.domain.Client;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

// Atelier 10 - Question 13 : Tests unitaires pour ClientService
@SpringBootTest
class ClientServiceTest {

    @Autowired
    private IClientService clientService;

    @Test
    void testCreateClient() {
        Client client = new Client();
        client.setNom("Client Test");
        client.setPrenom("Test");
        client.setEmail("test" + UUID.randomUUID().toString().substring(0, 5) + "@test.com");
        client.setTelephone("12345678");
        client.setDateInscription(LocalDate.now(ZoneId.systemDefault()));
        client.setNumPermis(UUID.randomUUID().toString().substring(0, 10));

        Client created = clientService.create(client);

        assertNotNull(created);
        assertNotNull(created.getIdClient());
        assertEquals("Client Test", created.getNom());
    }

    @Test
    void testFindById() {
        Client client = new Client();
        client.setNom("Client Find Test");
        client.setPrenom("Test");
        client.setEmail("find" + UUID.randomUUID().toString().substring(0, 5) + "@test.com");
        client.setTelephone("87654321");
        client.setDateInscription(LocalDate.now(ZoneId.systemDefault()));
        client.setNumPermis(UUID.randomUUID().toString().substring(0, 10));

        Client created = clientService.create(client);

        Client found = clientService.findById(created.getIdClient());

        assertNotNull(found);
        assertEquals(created.getIdClient(), found.getIdClient());
        assertEquals("Client Find Test", found.getNom());
    }

    @Test
    void testFindAll() {
        assertNotNull(clientService.findAll());
    }

    @Test
    void testUpdateClient() {
        Client client = new Client();
        client.setNom("Client Update Test");
        client.setPrenom("Test");
        client.setEmail("update" + UUID.randomUUID().toString().substring(0, 5) + "@test.com");
        client.setTelephone("11111111");
        client.setDateInscription(LocalDate.now(ZoneId.systemDefault()));
        client.setNumPermis(UUID.randomUUID().toString().substring(0, 10));

        Client created = clientService.create(client);

        created.setNom("Client Updated");
        created.setTelephone("22222222");

        Client updated = clientService.update(created.getIdClient(), created);

        assertNotNull(updated);
        assertEquals("Client Updated", updated.getNom());
        assertEquals("22222222", updated.getTelephone());
    }

    @Test
    void testDeleteById() {
        Client client = new Client();
        client.setNom("Client Delete Test");
        client.setPrenom("Test");
        client.setEmail("delete" + UUID.randomUUID().toString().substring(0, 5) + "@test.com");
        client.setTelephone("33333333");
        client.setDateInscription(LocalDate.now(ZoneId.systemDefault()));
        client.setNumPermis(UUID.randomUUID().toString().substring(0, 10));

        Client created = clientService.create(client);

        clientService.deleteById(created.getIdClient());

        assertThrows(Exception.class, () -> clientService.findById(created.getIdClient()));
    }

    @Test
    void testCreateClientWithLongPrenom() {
        Client client = new Client();
        client.setNom("Client Long Prenom");
        client.setPrenom("Jean-Christophe-Alexandre");
        client.setEmail("long" + UUID.randomUUID().toString().substring(0, 5) + "@test.com");
        client.setTelephone("12345678");
        client.setDateInscription(LocalDate.now(ZoneId.systemDefault()));
        client.setNumPermis(UUID.randomUUID().toString().substring(0, 10));

        Client created = clientService.create(client);
        assertNotNull(created);
        assertEquals("Jean-Christophe-Alexandre", created.getPrenom());
    }

    @Test
    void testCreateClientWithSpecialCharsInNom() {
        Client client = new Client();
        client.setNom("O'Connor");
        client.setPrenom("Test");
        client.setEmail("special" + UUID.randomUUID().toString().substring(0, 5) + "@test.com");
        client.setTelephone("87654321");
        client.setDateInscription(LocalDate.now(ZoneId.systemDefault()));
        client.setNumPermis(UUID.randomUUID().toString().substring(0, 10));

        Client created = clientService.create(client);
        assertNotNull(created);
        assertEquals("O'Connor", created.getNom());
    }

    @Test
    void testUpdateClientEmail() {
        Client client = new Client();
        client.setNom("Client Email Test");
        client.setPrenom("Test");
        client.setEmail("old" + UUID.randomUUID().toString().substring(0, 5) + "@test.com");
        client.setTelephone("11111111");
        client.setDateInscription(LocalDate.now(ZoneId.systemDefault()));
        client.setNumPermis(UUID.randomUUID().toString().substring(0, 10));

        Client created = clientService.create(client);
        created.setEmail("new" + UUID.randomUUID().toString().substring(0, 5) + "@test.com");

        Client updated = clientService.update(created.getIdClient(), created);
        assertNotNull(updated);
    }

    @Test
    void testUpdateClientPrenom() {
        Client client = new Client();
        client.setNom("Client Prenom Test");
        client.setPrenom("OldPrenom");
        client.setEmail("prenom" + UUID.randomUUID().toString().substring(0, 5) + "@test.com");
        client.setTelephone("22222222");
        client.setDateInscription(LocalDate.now(ZoneId.systemDefault()));
        client.setNumPermis(UUID.randomUUID().toString().substring(0, 10));

        Client created = clientService.create(client);
        created.setPrenom("NewPrenom");

        Client updated = clientService.update(created.getIdClient(), created);
        assertEquals("NewPrenom", updated.getPrenom());
    }

    @Test
    void testUpdateClientDateInscription() {
        Client client = new Client();
        client.setNom("Client Date Test");
        client.setPrenom("Test");
        client.setEmail("date" + UUID.randomUUID().toString().substring(0, 5) + "@test.com");
        client.setTelephone("33333333");
        client.setDateInscription(LocalDate.now(ZoneId.systemDefault()).minusDays(30));
        client.setNumPermis(UUID.randomUUID().toString().substring(0, 10));

        Client created = clientService.create(client);
        LocalDate newDate = LocalDate.now(ZoneId.systemDefault());
        created.setDateInscription(newDate);

        Client updated = clientService.update(created.getIdClient(), created);
        assertEquals(newDate, updated.getDateInscription());
    }
}
