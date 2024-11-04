package br.com.falzoni.falzoni_java_api.services.base;

import java.util.List;
import java.util.UUID;

public abstract class AbstractService<T> {
    public abstract List<T> findAll();
    public abstract T findById(UUID id);
    public abstract void update(T obj);
    public abstract void insert(T obj);
    public abstract void delete(T obj);
}
