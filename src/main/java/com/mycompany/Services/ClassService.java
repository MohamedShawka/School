package com.mycompany.Services;

import com.mycompany.Entities.ClassEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class ClassService {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public List<ClassEntity> findAll() {
        return em.createQuery("SELECT c FROM ClassEntity c", ClassEntity.class).getResultList();
    }

    public void save(ClassEntity c) {
        if (c.getClassID() == null) {
            em.persist(c);
        } else {
            em.merge(c);
        }
    }

    public void delete(ClassEntity c) throws Exception {
        ClassEntity Ce = em.find(ClassEntity.class, c.getClassID());
        if (Ce != null && Ce.getStudentList() != null && !Ce.getStudentList().isEmpty()) {
            throw new Exception("The class cannot be deleted because it contains students.");
        }
        em.remove(Ce);
    }

    public ClassEntity findByName(String className) {
        try {
            return em.createQuery("SELECT c FROM ClassEntity c WHERE c.className = :className", ClassEntity.class)
                    .setParameter("className", className)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void update(ClassEntity entity) {
        if (entity != null && entity.getClassID() != null)
        em.merge(entity);
    }

}
