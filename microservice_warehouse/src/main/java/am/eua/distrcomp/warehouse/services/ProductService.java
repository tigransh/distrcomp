package am.eua.distrcomp.warehouse.services;

import am.eua.distrcomp.warehouse.data.Product;
import am.eua.distrcomp.warehouse.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService{
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Retrieve a product by ID
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    // Add or update a product
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // Update product quantity
    public Optional<Product> updateQuantity(Long id, int newQuantity) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setQuantity(newQuantity);
            return Optional.of(productRepository.save(product));
        }
        return Optional.empty();
    }

    // Delete a product by ID
    public boolean deleteById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
