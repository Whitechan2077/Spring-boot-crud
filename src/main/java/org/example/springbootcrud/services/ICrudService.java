package org.example.springbootcrud.services;

import java.util.List;

public interface ICrudService<T,K> {
    List<T> getAll();
    T getById(K id);
    T insert(T dto);
    void update(T dto);
    void delete(K id);
    void deleteAll(List<K> ids);
}
