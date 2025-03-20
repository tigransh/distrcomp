package am.eua.distrcomp.warehouse.data;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Autowired
    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data (optional, for H2 in-memory DB)
        productRepository.deleteAll();

        // Add initial products
        productRepository.save(new Product("Laptop", 40, new BigDecimal("800.00")));
        productRepository.save(new Product("Book", 200, new BigDecimal("20.00")));
        productRepository.save(new Product("Pen", 1000, new BigDecimal("2.00")));

        // Log or verify (optional)
        System.out.println("Initial products loaded: " + productRepository.findAll());
    }
}
