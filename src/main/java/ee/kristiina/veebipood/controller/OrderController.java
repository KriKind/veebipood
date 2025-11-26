package ee.kristiina.veebipood.controller;

import ee.kristiina.veebipood.entity.Order;
import ee.kristiina.veebipood.entity.OrderRow;
import ee.kristiina.veebipood.entity.Person;
import ee.kristiina.veebipood.entity.Product;
import ee.kristiina.veebipood.model.ParcelMachine;
import ee.kristiina.veebipood.model.Supplier2;
import ee.kristiina.veebipood.repository.OrderRepository;
import ee.kristiina.veebipood.repository.ProductRepository;
import ee.kristiina.veebipood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    // tekitatakse mälukoht
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("orders")
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
    
    @PostMapping("order/{personId}")
    public Order createOrder(@RequestBody List<OrderRow> orderRows, @PathVariable("personId") Long personId){
        Order order = orderService.saveOrder(orderRows, personId);
        return orderService.makePayment(order.getId(), order.getTotal());
    }
    // localhost:8080/parcelmachines?country=EE
    // localhost:8080/parcelmachines
    @GetMapping("parcelmachines")
    public List<ParcelMachine> parcelmachines(@RequestParam(required = false) String country){


        System.out.println(restTemplate);
        String url = "https://www.omniva.ee/locations.json";
        // restTemplate(URL kuhu tehakse päring, Method, body + headers, andmetüüp mis tagastatakse)
        ParcelMachine[] body = restTemplate.exchange(url, HttpMethod.GET,null, ParcelMachine[].class).getBody();

        if (country != null) {
            return Arrays.stream(body)
                    .filter(e -> e.getA0_NAME().equals(country.toUpperCase()))
                    .toList();
        } else {
            return Arrays.stream(body)
                    .toList();
        }

    }



}
