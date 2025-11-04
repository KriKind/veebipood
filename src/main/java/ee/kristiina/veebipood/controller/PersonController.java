package ee.kristiina.veebipood.controller;

import ee.kristiina.veebipood.entity.Person;
import ee.kristiina.veebipood.entity.Product;
import ee.kristiina.veebipood.repository.CategoryRepository;
import ee.kristiina.veebipood.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("persons")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    //localhost:8080/products
    @PostMapping("signup")
    public Person signup(@RequestBody Person person){
        if(person.getId() != null){
            throw new RuntimeException("Cannot register when id present");
        }
        if(person.getEmail() == null || person.getEmail().isEmpty()){
            throw new RuntimeException("Email cannot be empty");
        }
        if(person.getPassword() ==null || person.getPassword().isEmpty()){
            throw new RuntimeException("Password cannot be empty");
        }
        return personRepository.save(person);
    }


    //localhost:8080/products
    @PutMapping("persons")
    public Person editPerson(@RequestBody Person person){
        if(person.getId() == null){
            throw new RuntimeException("Cannot edit when id missing");
        }
        if(person.getEmail() == null || person.getEmail().isEmpty()){
            throw new RuntimeException("Email cannot be empty");
        }
        if(person.getPassword() ==null || person.getPassword().isEmpty()){
            throw new RuntimeException("Password cannot be empty");
        }

        return personRepository.save(person);
    }

}
