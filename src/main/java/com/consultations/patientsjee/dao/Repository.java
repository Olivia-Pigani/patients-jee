package com.consultations.patientsjee.dao;

import com.consultations.patientsjee.utils.HibernateSession;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;

import java.util.List;

@Setter
@NoArgsConstructor
public abstract class Repository<T>{

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
