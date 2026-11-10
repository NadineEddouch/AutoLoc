package tn.esprit.autoloc.service;

import java.util.List;

// Chap 8 : question 1 - interface pour les opérations de lecture
public interface IReadService<T, ID> {
    T findById(ID id);
    List<T> findAll();
}
