package am.eua.distrcomp.shop.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;

    public Product() {

    }

    public Product(Long id, String name, BigDecimal price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }

    public int getQuantity() {
        return quantity;
    }

//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
}
