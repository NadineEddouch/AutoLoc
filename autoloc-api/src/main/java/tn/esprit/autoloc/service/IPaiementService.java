package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

// Chap 8 : question 3 - refactorisation pour étendre IReadService (lecture seule)
public interface IPaiementService extends IReadService<Paiement, Long> {
}
