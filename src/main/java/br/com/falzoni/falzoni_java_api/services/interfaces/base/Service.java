package br.com.falzoni.falzoni_java_api.services.interfaces.base;

import java.util.List;
import java.util.UUID;

public interface Service<TDto> {
    List<TDto> findAll();
    TDto findById(UUID id);
    void update(TDto obj);
    void insert(TDto obj);
    void delete(UUID id);
}
