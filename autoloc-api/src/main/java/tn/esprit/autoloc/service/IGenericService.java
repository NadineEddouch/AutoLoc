package tn.esprit.autoloc.service;

// Chap 8 : question 2 - interface générique complète qui étend les trois interfaces
public interface IGenericService<T, ID> extends IReadService<T, ID>, IWriteService<T, ID>, IDeleteService<ID> {
    boolean check(T entity, boolean isNew);
}
