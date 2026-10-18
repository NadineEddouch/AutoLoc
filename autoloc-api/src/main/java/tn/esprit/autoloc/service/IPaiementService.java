package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

// Chap 5: Question 20 - Paiement lecture seule
public interface IPaiementService {
    Paiement findById(Long id);
    List<Paiement> findAll();
}
