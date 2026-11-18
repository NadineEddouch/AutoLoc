package tn.esprit.autoloc.service;

// Chap 8 : question 1 - interface pour les opérations de suppression
// Atelier 10 - Question 11 : Renommer les types génériques pour SonarQube
public interface IDeleteService<T2> {
    void deleteById(T2 id);
}
