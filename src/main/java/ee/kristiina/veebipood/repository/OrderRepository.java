package ee.kristiina.veebipood.repository;

import ee.kristiina.veebipood.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findByPerson_Id(Long id);
}
