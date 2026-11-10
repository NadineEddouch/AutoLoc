package tn.esprit.autoloc.service;

// Chap 8 : question 1 - interface pour les opérations d'écriture
public interface IWriteService<T, ID> {
    T create(T entity);
    T update(ID id, T entity);
}
