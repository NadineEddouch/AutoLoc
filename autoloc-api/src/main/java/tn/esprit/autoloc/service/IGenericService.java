package tn.esprit.autoloc.service;

// Chap 8 : question 2 - interface générique complète qui étend les trois interfaces
// Atelier 10 - Question 11 : Renommer les types génériques pour SonarQube
public interface IGenericService<T1, T2> extends IReadService<T1, T2>, IWriteService<T1, T2>, IDeleteService<T2> {
    boolean check(T1 entity, boolean isNew);
}
