package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.domain.Contrat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContratServiceTest {

    @Autowired
    private IContratService contratService;

    @Test
    void testCreateContrat() {
        Contrat contrat = new Contrat();
        contrat.setDateSignature(LocalDate.now(ZoneId.systemDefault()));
        contrat.setMontantTotal(new BigDecimal("1000"));
        contrat.setValide(true);

        Contrat created = contratService.create(contrat);
        assertNotNull(created);
        assertNotNull(created.getIdContrat());
        assertEquals(new BigDecimal("1000"), created.getMontantTotal());
    }

    @Test
    void testFindById() {
        Contrat contrat = new Contrat();
        contrat.setDateSignature(LocalDate.now(ZoneId.systemDefault()));
        contrat.setMontantTotal(new BigDecimal("2000"));
        contrat.setValide(false);

        Contrat created = contratService.create(contrat);
        Contrat found = contratService.findById(created.getIdContrat());

        assertNotNull(found);
        assertEquals(created.getIdContrat(), found.getIdContrat());
    }

    @Test
    void testFindAll() {
        assertNotNull(contratService.findAll());
    }

    @Test
    void testUpdateContrat() {
        Contrat contrat = new Contrat();
        contrat.setDateSignature(LocalDate.now(ZoneId.systemDefault()));
        contrat.setMontantTotal(new BigDecimal("1500"));
        contrat.setValide(false);

        Contrat created = contratService.create(contrat);
        created.setMontantTotal(new BigDecimal("2000"));
        created.setValide(true);

        Contrat updated = contratService.update(created.getIdContrat(), created);
        assertEquals(new BigDecimal("2000"), updated.getMontantTotal());
        assertTrue(updated.getValide());
    }

    @Test
    void testCreateContratWithMontantZero() {
        Contrat contrat = new Contrat();
        contrat.setDateSignature(LocalDate.now(ZoneId.systemDefault()));
        contrat.setMontantTotal(BigDecimal.ZERO);
        contrat.setValide(false);

        Contrat created = contratService.create(contrat);
        assertEquals(BigDecimal.ZERO, created.getMontantTotal());
    }



    @Test
    void testUpdateContratDateSignature() {
        Contrat contrat = new Contrat();
        contrat.setDateSignature(LocalDate.now(ZoneId.systemDefault()).minusDays(10));
        contrat.setMontantTotal(new BigDecimal("800"));
        contrat.setValide(true);

        Contrat created = contratService.create(contrat);
        LocalDate newDate = LocalDate.now(ZoneId.systemDefault());
        created.setDateSignature(newDate);

        Contrat updated = contratService.update(created.getIdContrat(), contrat);
        assertEquals(newDate, updated.getDateSignature());
    }
}
