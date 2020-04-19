package com.avatarduel.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, K> {

    public Optional<T> get(K k);

    public List<T> getAll();

    public void save(T t);

    public void delete(T t);

    public void update(K id, T t);
}