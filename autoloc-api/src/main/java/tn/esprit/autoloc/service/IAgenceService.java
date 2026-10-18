package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

// Chap 5: Question 5
// Chap 5: Question 16 - hérite de IGenericService
public interface IAgenceService extends IGenericService<Agence, Long> {
    boolean hasVehicules(Long id);
}
