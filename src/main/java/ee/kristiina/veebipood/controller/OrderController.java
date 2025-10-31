package ee.kristiina.veebipood.controller;

import ee.kristiina.veebipood.entity.Order;
import ee.kristiina.veebipood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
}
