package jpabook.jpashop.repository;

import jpabook.jpashop.domain.TestA;
import jpabook.jpashop.domain.TestB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class TestBRepository {

    private final EntityManager em;

    public Long save(TestB testB) {
        em.persist(testB);
        return testB.getTestBId();
    }

    public TestB findOne(Long id) {
        return em.find(TestB.class, id);
    }

}
