package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IPaiementRepository;

import java.util.List;

// Chap 5: Question 20 - Paiement lecture seule
@Service
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository repository;

    public PaiementServiceImpl(IPaiementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Paiement findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paiement introuvable : " + id));
    }

    @Override
    public List<Paiement> findAll() {
        return (List<Paiement>) repository.findAll();
    }
}
