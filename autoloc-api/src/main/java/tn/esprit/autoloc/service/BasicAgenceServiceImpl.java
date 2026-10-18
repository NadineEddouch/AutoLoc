package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;
// Chap 5: Question 6
@Service
@RequiredArgsConstructor
public class BasicAgenceServiceImpl implements IAgenceService {
    private final IAgenceRepository repository;

    // Chap 5: Question 7
    @Override
    public boolean check(Agence agence, boolean isNew) {
        return Utils.isNotEmpty(agence.getNom()) && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public Agence create(Agence agence) {
        if (agence.getIdAgence() != null) {
            throw new IllegalArgumentException("L'identifiant doit être vide à la création");
        }

        if (!check(agence, true)) {
            throw new IllegalArgumentException("Agence invalide");
        }
        return repository.save(agence);
    }

    // Chap 5: Question 8
    @Override
    public Agence findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Agence introuvable : ", id.toString()));
    }



    @Override
    public List<Agence> findAll() {
        return (List<Agence>) repository.findAll();
    }

    @Override
    public Agence update(Long id, Agence agence) {
        // Retrieve existing agency (throws if not found)
        Agence persisted = findById(id);

        // Validate incoming data (isNew = false)
        if (!check(agence, false)) {
            throw new IllegalArgumentException("Agence invalide");
        }

        // Update fields
        persisted.setNom(agence.getNom());
        persisted.setVille(agence.getVille());
        persisted.setAdresse(agence.getAdresse());
        persisted.setTelephone(agence.getTelephone());
        persisted.setVehicules(agence.getVehicules());

        // Save and return updated entity
        return repository.save(persisted);
    }

    // Chap 5: Question 10
    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Agence introuvable", id.toString());
        }
        repository.deleteById(id);
    }

    @Override
    public boolean hasVehicules(Long id) {
        return false;
    }
}
