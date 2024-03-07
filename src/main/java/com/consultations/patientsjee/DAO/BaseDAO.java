package com.consultations.patientsjee.DAO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;

import java.util.List;

@Setter
@NoArgsConstructor
public abstract class BaseDAO<T>{

    protected Session session;


     public void add(T element){
        session.persist(element);
     }
    public void update( T element){
        session.update(element);
    }

    public void delete(T element){
        session.remove(element);
    }

    public abstract T getById(long elementId);

    public abstract List<T> getAll();


}
