package tn.esprit.autoloc.service;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;

// Chap 5: Question 15 - Classe générique abstraite
// Atelier 10 - Question 11 : Renommer les types génériques pour SonarQube
public abstract class GenericServiceImpl<T1, T2, T3 extends CrudRepository<T1, T2>> implements IGenericService<T1, T2> {

    protected final T3 repository;

    protected GenericServiceImpl(T3 repository) {
        this.repository = repository;
    }

    @Override
    public boolean check(T1 entity, boolean isNew) {
        return true;
    }

    @Override
    public T1 create(T1 entity) {
        if (getId(entity) != null) {
            throw new IllegalArgumentException("L'identifiant doit être vide à la création");
        }
        if (!check(entity, true)) {
            throw new IllegalArgumentException(getEntityName() + " invalide");
        }
        return repository.save(entity);
    }

    @Override
    public T1 findById(T2 id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(getEntityName() + " introuvable", id.toString()));
    }

    @Override
    public List<T1> findAll() {
        return (List<T1>) repository.findAll(); // TODO: SonarQube S1905 - Le cast est nécessaire car CrudRepository.findAll() retourne Iterable
    }

    @Override
    public T1 update(T2 id, T1 entity) {
        T1 persisted = findById(id);
        if (!check(entity, false)) {
            throw new IllegalArgumentException(getEntityName() + " invalide");
        }
        setFields(entity, persisted);
        return repository.save(persisted);
    }

    @Override
    public void deleteById(T2 id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(getEntityName() + " introuvable", id.toString());
        }
        repository.deleteById(id);
    }

    // Méthodes abstraites à implémenter par les classes filles
    protected abstract String getEntityName();
    protected abstract T2 getId(T1 entity);
    protected abstract void setFields(T1 source, T1 target);
}
