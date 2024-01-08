package org.example.springbootcrud.services;

import java.util.List;

public interface ICrudService<T,K> {
    List<T> getAll();
    T getById();
    void insert(T dto);
    void update(T dto);
    void delete(K id);
    void deleteAll(List<K> ids);
}
