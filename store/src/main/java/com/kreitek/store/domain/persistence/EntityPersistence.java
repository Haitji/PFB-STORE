package com.kreitek.store.domain.persistence;

import com.kreitek.store.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface EntityPersistence <T>{
    //Para usar esto simplemente tendriamos que extender esta clase a CategoryPersistence, como lo hemos hecho en mappers
    List<T> getAll();
    Optional<T> getById(Long id);
    T save(T t);
    void delete(Long id);
}
