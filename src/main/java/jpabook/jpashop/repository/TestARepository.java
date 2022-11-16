package jpabook.jpashop.repository;

import jpabook.jpashop.domain.TestA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class TestARepository {

    private final EntityManager em;

    public Long save(TestA testA) {
        em.persist(testA);
        return testA.getTestAId();
    }

    public TestA findOne(Long id) {
        return em.find(TestA.class, id);
    }

}
