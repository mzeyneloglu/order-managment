package managment.productservice.model;

import jakarta.persistence.*;
import jakarta.ws.rs.DefaultValue;
import lombok.Getter;
import lombok.Setter;
import managment.productservice.model.dto.ProductDTO;

@Entity
@Getter
@Setter
@Table(name = "MN_PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TICKET_NO")
    private String ticketNo;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "DISCOUNT")
    private double discount = 0.0;
}
