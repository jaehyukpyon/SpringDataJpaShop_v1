package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ParentRepository {

    @PersistenceContext
    private EntityManager em;

    public Parent findOne(Long parentId) {
        return em.find(Parent.class, parentId);
    }

    public List<Parent> findAllParent() {
        return em.createQuery("SELECT p FROM Parent p", Parent.class).getResultList();
    }

}
