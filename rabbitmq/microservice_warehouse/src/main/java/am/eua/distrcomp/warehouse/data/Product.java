package am.eua.distrcomp.warehouse.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int quantity;

    @Getter
    @Setter
    private BigDecimal price;

    public Product(String name, int quantity, BigDecimal price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public Product(){

    }
}
