package ee.kristiina.veebipood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime updated;

}
