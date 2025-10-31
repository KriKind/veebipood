package ee.kristiina.veebipood.repository;

import ee.kristiina.veebipood.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order,Integer> {
}
