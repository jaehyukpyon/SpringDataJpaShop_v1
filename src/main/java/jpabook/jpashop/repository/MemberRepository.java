package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        List<Member> memberList = em.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
        return memberList;
    }

    public List<Member> findByName(String name) {
        System.out.println("MemberRepository findByName starts...");
        List<Member> memberList = em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        System.out.println("findByName.size() = " + memberList.size());
        return memberList;
    }

}
