package ee.kristiina.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int quantity;

    // @ManytoMany
    // @ManytoOne
    // @OnetoMany
    // OnetoOne

    @ManyToOne
    private Category category;

    // @ManytoMany gluteenivaba, laktoosivaba
    // private List<Characteristic> characteristics

    // @OneToOne
    // private Toitained toitained;

    // @OnetoMany
    // private List<TranslatedNames> translatedNames



}
