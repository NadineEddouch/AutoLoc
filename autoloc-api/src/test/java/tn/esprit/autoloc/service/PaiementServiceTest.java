package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaiementServiceTest {

    @Autowired
    private IPaiementService paiementService;

    @Test
    void testFindAll() {
        assertNotNull(paiementService.findAll());
    }

    @Test
    void testFindByIdNonExistent() {
        // IPaiementService est read-only, on teste que findById lève une exception pour un ID inexistant
        assertThrows(Exception.class, () -> paiementService.findById(999999L));
    }
}
