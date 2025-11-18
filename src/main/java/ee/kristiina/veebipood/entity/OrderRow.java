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
public class OrderRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    // @ManytoMany xx, sest siis peaks olema List<Products>
    // @ManytoOne --> kellelgi teisel OrderRow-l võib olla sama toode
    // @OnetoMany xx, sest siis peaks olema List<Products>
    // OnetoOne --> kellelgi teisel ei tohi olla, üks ühele seos
    @ManyToOne
    private  Product product;
}
