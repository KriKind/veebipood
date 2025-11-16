package ee.kristiina.veebipood.controller;

import ee.kristiina.veebipood.entity.Product;
import ee.kristiina.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
// saab frontendis ligi
@CrossOrigin("http://localhost:5173/") // "*" kõik clientid saavad ligi

@RestController
public class ProductController {

    @Autowired //Dependency Injection
    private ProductRepository productRepository;

    //localhost:8080/products
    @GetMapping("products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    //localhost:8080/products
    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product){
        if(product.getId() != null){
            throw new RuntimeException("Cannot edit when id is present");
        }
        if(product.getName() == null || product.getName().isEmpty()){
            throw new RuntimeException("Cannot be empty");
        }
        if(product.getPrice() <=0){
            throw new RuntimeException("Cannot be neg");
        }
        productRepository.save(product);
        return productRepository.findAll();
    }

    // variant 1
    //localhost:8080/products?id=2

    // mitu argumenti - @RequestParam
    // Kui on 1 või miitu vabatahtlikku argumenti - @RequestParam
    @DeleteMapping("products")
    public List<Product> deleteProduct(@RequestParam Long id){
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    // variant 2
    // localhost:8080/products/2/

    // Kui on täpselt 1 argument ja saan aru millega on tegemist
    // DELETE localhost:8080/products/2
    @DeleteMapping("products2/{id}")
    public List<Product> deleteProduct2(@PathVariable Long id){
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    // ühe võtmine
    //localhost:8080/products/3
    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id){

        //return productRepository.findById(id).get();
        //return productRepository.findById(id).orElse(null);
        return productRepository.findById(id).orElseThrow();
    }

    // muutmine terviku
    //localhost:8080/products
    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product){
        if(product.getId() == null || product.getId() <= 0){
            throw new RuntimeException("Cannot edit when id is null or empty");
        }
        if(product.getName() == null || product.getName().isEmpty()){
            throw new RuntimeException("Cannot edit when name is null or empty");
        }
        if(product.getPrice() <=0){
            throw new RuntimeException("Cannot edit when price is neg");
        }
        productRepository.save(product);
        return productRepository.findAll();
    }

    // http status code
    // 2xx --> edukas päring
    // 4xx --> päringu tegija viga (front end viga) client error
    // 5xx --> päringu vastuvõtja viga
}
