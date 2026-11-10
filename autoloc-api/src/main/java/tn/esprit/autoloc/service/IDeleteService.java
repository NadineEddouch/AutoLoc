package tn.esprit.autoloc.service;

// Chap 8 : question 1 - interface pour les opérations de suppression
public interface IDeleteService<ID> {
    void deleteById(ID id);
}
