package com.consultations.patientsjee.dao;

import java.util.List;

public interface BaseDao<T>{


    public boolean add(T element);

    public T getById(long elementId);

    public List<T> getAll();

    public boolean update(long elementId, T element);

    public boolean delete(long elementId);

}
