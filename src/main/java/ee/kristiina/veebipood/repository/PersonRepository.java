package ee.kristiina.veebipood.repository;

import ee.kristiina.veebipood.entity.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findByEmail(String email);


    List<Person> findByOrderByIdAsc();

}
