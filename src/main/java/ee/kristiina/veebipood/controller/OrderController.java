package ee.kristiina.veebipood.controller;

import ee.kristiina.veebipood.entity.Order;
import ee.kristiina.veebipood.entity.Person;
import ee.kristiina.veebipood.entity.Product;
import ee.kristiina.veebipood.repository.OrderRepository;
import ee.kristiina.veebipood.repository.ProductRepository;
import ee.kristiina.veebipood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("orders")
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
    
    @PostMapping("order/{personId}")
    public Order createOrder(@RequestBody List<Product> products, @PathVariable("personId") Long personId){
        return orderService.saveOrder(products, personId);
    }


}
