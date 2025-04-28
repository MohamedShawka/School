package com.mycompany.Services;

import com.mycompany.Entities.ClassEntity;
import com.mycompany.Entities.Student;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StudentService {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public List<Student> findAll() {
        return em.createQuery("SELECT s FROM Student s join fetch s.classEntity", Student.class).getResultList();
    }

    public void save(Student s) {
        if (s.getStudentID() == null) {
            em.persist(s);
        } else {
            em.merge(s);
        }
    }

    public void delete(Student s) {
        em.remove(em.merge(s));
    }

    public void update(Student s) {
        em.merge(s);
    }

}
