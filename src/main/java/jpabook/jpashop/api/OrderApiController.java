package jpabook.jpashop.api;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.order.query.OrderFlatDto;
import jpabook.jpashop.repository.order.query.OrderItemQueryDto;
import jpabook.jpashop.repository.order.query.OrderQueryDto;
import jpabook.jpashop.repository.order.query.OrderQueryRepository;
import jpabook.jpashop.service.query.OrderDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    
    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(orderItem -> orderItem.getItem().getName());
        }
        return all;
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> collect;
        collect = orders.stream()
                .map(OrderDto::new)
                .collect(toList());

        return collect;
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithItem();

        List<OrderDto> collect = orders.stream()
                .map(OrderDto::new)
                .collect(toList());

        return collect;
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(@RequestParam(value = "offset",defaultValue = "0") int offset,
                                        @RequestParam(value = "limit",defaultValue = "100") int limit){

        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset,limit);

        List<OrderDto> collect = orders.stream()
                .map(OrderDto::new)
                .collect(toList());

        return collect;
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4(){
        return orderQueryRepository.findOrderQueryDtos();
    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5(){
        return orderQueryRepository.findAllByDto_optimization();
    }

    @GetMapping("/api/v6/orders")
    public List<OrderQueryDto> ordersV6(){
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();

       return flats.stream()
                .collect(groupingBy(o -> new OrderQueryDto(o.getOrderId(),
                                o.getName(), o.getOrderDate(), o.getOrderStatus(), o.getAddress()),
                        mapping(o -> new OrderItemQueryDto(o.getOrderId(),
                                o.getItemName(), o.getOrderPrice(), o.getCount()), toList())
                )).entrySet().stream()
                .map(e -> new OrderQueryDto(e.getKey().getOrderId(),
                        e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(),
                        e.getKey().getAddress(), e.getValue()))
                .collect(toList());
    }

//    @Getter
//    static class OrderDto {
//
//        private Long orderId;
//        private String name;
//        private LocalDateTime orderDate;
//        private OrderStatus orderStatus;
//        private Address address;
//        private List<OrderItemDto> orderItems;
//
//        public OrderDto(Order order) {
//            orderId = order.getId();
//            name = order.getMember().getName();
//            orderDate = order.getOrderDate();
//            orderStatus = order.getStatus();
//            address = order.getDelivery().getAddress();
//            orderItems = order.getOrderItems().stream()
//                    .map(OrderItemDto::new)
//                    .collect(toList());
////            order.getOrderItems().stream().forEach(orderItem -> orderItem.getItem().getName());
////            orderItems = order.getOrderItems();
//        }
//    }
//
//    @Getter
//    static class OrderItemDto {
//        private String itemName; // 상품명
//        private int orderPrice; // 수문 가격
//        private int count; // 주문 수량
//        public OrderItemDto(OrderItem orderItem) {
//            itemName = orderItem.getItem().getName();
//            orderPrice = orderItem.getOrderPrice();
//            count = orderItem.getCount();
//        }
//    }

}
