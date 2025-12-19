package ee.kristiina.veebipood.controller;

import ee.kristiina.veebipood.entity.Order;
import ee.kristiina.veebipood.entity.OrderRow;
import ee.kristiina.veebipood.model.ParcelMachine;
import ee.kristiina.veebipood.repository.OrderRepository;
import ee.kristiina.veebipood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

//@CrossOrigin("http://localhost:5173")
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

    @GetMapping("my-orders")
    public List<Order> getPersonOrders(){
        Long personId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return orderRepository.findByPerson_Id(personId);
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


    @PostMapping("order")
    public String createOrder(@RequestBody List<OrderRow> orderRows) throws ExecutionException {
        Long personId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        Order order = orderService.saveOrder(orderRows, personId);
        return orderService.makePayment(order.getId(), order.getTotal());
    }

}
