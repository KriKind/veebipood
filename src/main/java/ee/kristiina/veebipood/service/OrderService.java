package ee.kristiina.veebipood.service;

import ee.kristiina.veebipood.entity.Order;
import ee.kristiina.veebipood.entity.OrderRow;
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

    public Order saveOrder(List<OrderRow> orderRows, Long personId) {
        Order order = new Order();
        order.setCreated(new Date());
        order.setOrderRows(orderRows);

        double sum = 0;
        for (OrderRow orderRow : orderRows){
            Product dbProduct = productRepository.findById(orderRow.getProduct().getId()).orElseThrow();
            sum += dbProduct.getPrice() * orderRow.getQuantity();
        }
        order.setTotal(sum);

        Person person = new Person();
        person.setId(personId);
        order.setPerson(person); // hiljem audentimisest

        return orderRepository.save(order);
    }

    public String makePayment(Long id, double total) {
//        {
//            "account_name": "EUR3D1", // konto nimi kuhu raha läheb
//            "nonce": "158324ab51fkm", // turvaelement, peab iga päring olema unikaalne
//            "timestamp": "2025-11-26T09:59:32Z", // turvaelement, ajatempel
//            "amount": 47,  // summa, kuni 7000EUR saab olla, üle selle error
//            "order_reference": "8ee68a6c", // tellimuse nr, kui on makstud, siis enam ei saa maksta
//            "customer_url": "https://err.ee", // kuhu tagasi suunatakse, ei saa olla localhost
//            "api_username": "e36eb40f5ec87fa2" // kasutajanimi, mis peab ühtima Authorization-ga
 //       }
        return "";
    }
}
