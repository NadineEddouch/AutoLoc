package tn.esprit.autoloc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.StatutReservation;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private IReservationService reservationService;

    @Test
    void testFindAll() {
        assertNotNull(reservationService.findAll());
    }

    @Test
    void testCreateReservation() {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        reservation.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(3));
        reservation.setStatut(StatutReservation.CONFIRMEE);

        Reservation created = reservationService.create(reservation);
        assertNotNull(created);
        assertNotNull(created.getIdReservation());
        assertEquals(StatutReservation.CONFIRMEE, created.getStatut());
    }

    @Test
    void testFindById() {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        reservation.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(5));
        reservation.setStatut(StatutReservation.EN_ATTENTE);

        Reservation created = reservationService.create(reservation);
        Reservation found = reservationService.findById(created.getIdReservation());

        assertNotNull(found);
        assertEquals(created.getIdReservation(), found.getIdReservation());
    }

    @Test
    void testUpdateReservation() {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        reservation.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(2));
        reservation.setStatut(StatutReservation.EN_ATTENTE);

        Reservation created = reservationService.create(reservation);
        created.setStatut(StatutReservation.CONFIRMEE);

        Reservation updated = reservationService.update(created.getIdReservation(), created);
        assertEquals(StatutReservation.CONFIRMEE, updated.getStatut());
    }

    @Test
    void testCreateReservationConfirmee() {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        reservation.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(4));
        reservation.setStatut(StatutReservation.CONFIRMEE);

        Reservation created = reservationService.create(reservation);
        assertEquals(StatutReservation.CONFIRMEE, created.getStatut());
    }

    @Test
    void testCreateReservationEnAttente() {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        reservation.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(7));
        reservation.setStatut(StatutReservation.EN_ATTENTE);

        Reservation created = reservationService.create(reservation);
        assertEquals(StatutReservation.EN_ATTENTE, created.getStatut());
    }

    @Test
    void testCreateReservationAnnulee() {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        reservation.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(1));
        reservation.setStatut(StatutReservation.ANNULEE);

        Reservation created = reservationService.create(reservation);
        assertEquals(StatutReservation.ANNULEE, created.getStatut());
    }

    @Test
    void testUpdateReservationDates() {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(LocalDate.now(ZoneId.systemDefault()));
        reservation.setDateFin(LocalDate.now(ZoneId.systemDefault()).plusDays(3));
        reservation.setStatut(StatutReservation.EN_ATTENTE);

        Reservation created = reservationService.create(reservation);
        LocalDate newFin = LocalDate.now(ZoneId.systemDefault()).plusDays(10);
        created.setDateFin(newFin);

        Reservation updated = reservationService.update(created.getIdReservation(), created);
        assertEquals(newFin, updated.getDateFin());
    }

    @Test
    void testUpdateReservationStatutToTerminee() {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(LocalDate.now(ZoneId.systemDefault()).minusDays(5));
        reservation.setDateFin(LocalDate.now(ZoneId.systemDefault()).minusDays(2));
        reservation.setStatut(StatutReservation.CONFIRMEE);

        Reservation created = reservationService.create(reservation);
        created.setStatut(StatutReservation.TERMINEE);

        Reservation updated = reservationService.update(created.getIdReservation(), created);
        assertEquals(StatutReservation.TERMINEE, updated.getStatut());
    }
}
