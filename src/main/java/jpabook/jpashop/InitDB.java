package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.TestARepository;
import jpabook.jpashop.repository.TestBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        private final TestARepository testARepository;
        private final TestBRepository testBRepository;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "2222");
            em.persist(member);

            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInitTestAandB() {
            TestB testB = new TestB();
            testB.setTestBId(100L);
            testB.setTestBName("testB");
            em.persist(testB);

            TestA testA = new TestA();
            testA.setTestAId(1000L);
            testA.setTestAName("testA");
            testA.setTestB(testB);
            em.persist(testA);
        }

        public void dbInitParentChild() {
            Parent parent1 = new Parent();
            parent1.setParentId(1000L);
            parent1.setParentName("parent1");
            em.persist(parent1);

            Child child1 = new Child();
            child1.setChildId(1111L);
            child1.setChildName("child1");
            child1.setParent(parent1);
            em.persist(child1);

            Child child2 = new Child();
            child2.setChildId(2222L);
            child2.setChildName("child2");
            child2.setParent(parent1);
            em.persist(child2);

            Parent parent2 = new Parent();
            parent2.setParentId(2000L);
            parent2.setParentName("parent2");
            em.persist(parent2);

            Child child3 = new Child();
            child3.setChildId(3333L);
            child3.setChildName("child3");
            child3.setParent(parent2);
            em.persist(child3);

            Child child4 = new Child();
            child4.setChildId(4444L);
            child4.setChildName("child4");
            child4.setParent(parent2);
            em.persist(child4);
        }
    }

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();

        initService.dbInitTestAandB();

        initService.dbInitParentChild();
    }

}
