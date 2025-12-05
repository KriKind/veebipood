package ee.kristiina.veebipood.controller;

import ee.kristiina.veebipood.dto.PersonDTO;
import ee.kristiina.veebipood.entity.Category;
import ee.kristiina.veebipood.entity.Person;
import ee.kristiina.veebipood.model.AuthToken;
import ee.kristiina.veebipood.model.LoginCredentials;
import ee.kristiina.veebipood.repository.PersonRepository;
import ee.kristiina.veebipood.service.JwtService;
import ee.kristiina.veebipood.service.PersonService;
import ee.kristiina.veebipood.util.Validations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("persons")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @GetMapping("public-persons")
    public List<PersonDTO> getPublicPersons() {
//        List<Person> persons = personRepository.findAll();
//        List<PersonDTO> personDTOs = new ArrayList<>();
//        for (Person person : persons) {
//            PersonDTO personDTO = new PersonDTO();
//            personDTO.setFirstName(person.getFirstName());
//            personDTO.setLastName(person.getLastName());
//            personDTOs.add(personDTO);
//        }
//        return personDTOs;


        return List.of(modelMapper.map(personRepository.findAll(), PersonDTO[].class));

    }

    //localhost:8080/products
    @PostMapping("signup")
    public Person signup(@RequestBody Person person){
        return personService.savePerson(person);
    }


    //localhost:8080/persons
    @PutMapping("persons")
    public Person editPerson(@RequestBody Person person){
        if(person.getId() == null){
            throw new RuntimeException("Cannot edit when id missing");
        }
        if(person.getEmail() == null || person.getEmail().isEmpty()){
            throw new RuntimeException("Email cannot be empty");
        }
        if(!Validations.validateEmail(person.getEmail())){
            throw new RuntimeException("Email is not valid");
        }
        Person dbPerson = personRepository.findByEmail(person.getEmail());
        if(dbPerson != null){
            throw new RuntimeException("Email already taken");
        }
//        if(person.getPassword() == null || person.getPassword().isEmpty()){
//            throw new RuntimeException("Password cannot be empty");
//        }
        return personRepository.save(person);
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<List<Person>> deleteCategory(@PathVariable Long id) {
        personRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findAll());
    }

    @PostMapping("login")
    public AuthToken login(@RequestBody LoginCredentials loginCredentials){
        AuthToken authToken = new AuthToken();
        authToken.setToken(personService.getToken(loginCredentials));
        return authToken;
    }

    @GetMapping("person")
    public Person getPerson() {
        Long personId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return personRepository.findById(personId).orElseThrow();
    }

}
