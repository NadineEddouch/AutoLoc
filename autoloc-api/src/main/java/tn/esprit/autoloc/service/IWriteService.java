package tn.esprit.autoloc.service;

// Chap 8 : question 1 - interface pour les opérations d'écriture
// Atelier 10 - Question 11 : Renommer les types génériques pour SonarQube
public interface IWriteService<T1, T2> {
    T1 create(T1 entity);
    T1 update(T2 id, T1 entity);
}
