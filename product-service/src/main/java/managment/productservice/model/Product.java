package managment.productservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "MN_PRODUCT")
// @ApiModel(value = "Product model", description = "This model describes a product entity")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "DISCOUNT")
    private BigDecimal discount;
}
