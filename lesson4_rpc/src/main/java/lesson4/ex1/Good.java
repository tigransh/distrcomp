package lesson4.ex1;

import java.io.Serializable;

public class Good implements Serializable {
    private final String name;
    private final double price;
    private int quantity;

    public Good(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void reduceQuantity(int q) { quantity -= q; }

    public String toString() {
        return name + " - $" + price + " - " + quantity + " in stock";
    }
}
