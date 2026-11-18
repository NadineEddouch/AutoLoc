package tn.esprit.autoloc.service;

import java.util.List;

// Chap 8 : question 1 - interface pour les opérations de lecture
// Atelier 10 - Question 11 : Renommer les types génériques pour SonarQube
public interface IReadService<T1, T2> {
    T1 findById(T2 id);
    List<T1> findAll();
}
