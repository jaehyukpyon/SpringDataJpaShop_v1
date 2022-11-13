package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {
        String query = "SELECT o " +
                "FROM Order o join o.member m " +
                "WHERE o.status = :status " +
                "AND m.name LIKE :name";

        List<Order> resultList = em.createQuery(query, Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .getResultList();

        return resultList;
    }

    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        return null;
    }

}
