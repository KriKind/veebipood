package ee.kristiina.veebipood.service;

import ee.kristiina.veebipood.entity.Order;
import ee.kristiina.veebipood.entity.Person;
import ee.kristiina.veebipood.entity.Product;
import ee.kristiina.veebipood.repository.OrderRepository;
import ee.kristiina.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order saveOrder(List<Product> products, Long personId) {
        Order order = new Order();
        order.setCreated(new Date());
        order.setProducts(products);

        double sum = 0;
        for (Product product : products){
            Product dbProduct = productRepository.findById(product.getId()).orElseThrow();
            sum += dbProduct.getPrice();
        }
        order.setTotal(sum);

        Person person = new Person();
        person.setId(personId);
        order.setPerson(person); // hiljem audentimisest

        return orderRepository.save(order);
    }
}
