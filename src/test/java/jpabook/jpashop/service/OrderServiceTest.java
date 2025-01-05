package jpabook.jpashop.service;

import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 상품주문 () throws Exception{
        //given

        //when

        //then
    }

    @Test
    public void 주문취소 () throws Exception{
        //given

        //when

        //then
    }

    @Test
    public void 상품주문_재고수량초과 () throws Exception{
        //given

        //when

        //then
    }
}