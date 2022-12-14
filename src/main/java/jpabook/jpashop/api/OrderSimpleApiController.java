package jpabook.jpashop.api;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.*;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    private final TestARepository testARepository;

    private final TestBRepository testBRepository;

    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping(value = "/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
        }
        return all;
    }

    @GetMapping(value = "/api/testa/{id}")
    public TestA findTestA(@PathVariable("id") Long id) {
        return testARepository.findOne(id); // bytebuddy exception 발생
    }

    @GetMapping(value = "/api/testa2/{id}")
    public TestA findTestA2(@PathVariable("id") Long id) {
        TestA findTestA = testARepository.findOne(id);
        System.out.println("========== before ==========");
        findTestA.getTestB().getTestBName();
        System.out.println("========== after ==========");
        return findTestA; // bytebuddy exception 발생
    }

    @GetMapping(value = "/api/testb/{id}")
    public TestB findTestB(@PathVariable("id") Long id) {
        return testBRepository.findOne(id);
    }

    @GetMapping(value = "/api/testb2/{id}")
    public TestB findTestB2(@PathVariable("id") Long id) {
        TestB find = testBRepository.findOne(id);
        find.getTestAList().size();
        return find;
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); // LAZY 초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); // LAZY 초기화
        }
    }

    // JOIN FETCH 사용 X
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
    }

    // JOIN FETCH 사용 O
    @GetMapping(value = "/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
    }

    @GetMapping(value = "/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

}
