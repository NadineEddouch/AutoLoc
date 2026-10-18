package tn.esprit.autoloc.service;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

import java.util.List;

// Chap 5: Question 15 - Classe générique abstraite
public abstract class GenericServiceImpl<T, ID, R extends CrudRepository<T, ID>> implements IGenericService<T, ID> {

    protected final R repository;

    protected GenericServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public boolean check(T entity, boolean isNew) {
        return true;
    }

    @Override
    public T create(T entity) {
        if (getId(entity) != null) {
            throw new IllegalArgumentException("L'identifiant doit être vide à la création");
        }
        if (!check(entity, true)) {
            throw new IllegalArgumentException(getEntityName() + " invalide");
        }
        return repository.save(entity);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(getEntityName() + " introuvable", id.toString()));
    }

    @Override
    public List<T> findAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    public T update(ID id, T entity) {
        T persisted = findById(id);
        if (!check(entity, false)) {
            throw new IllegalArgumentException(getEntityName() + " invalide");
        }
        setFields(entity, persisted);
        return repository.save(persisted);
    }

    @Override
    public void deleteById(ID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(getEntityName() + " introuvable", id.toString());
        }
        repository.deleteById(id);
    }

    // Méthodes abstraites à implémenter par les classes filles
    protected abstract String getEntityName();
    protected abstract ID getId(T entity);
    protected abstract void setFields(T source, T target);
}
