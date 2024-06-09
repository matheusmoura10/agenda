package com.wareline.agenda.shared.repository;

import java.util.List;

public interface BaseRepositotyInterface<T> {
    public List<T> findAll();
    public T findById(Long id);
    public T save(T entity);
    public void delete(T entity);
}
