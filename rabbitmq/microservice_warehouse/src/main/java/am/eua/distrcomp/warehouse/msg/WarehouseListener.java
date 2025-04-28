package am.eua.distrcomp.warehouse.msg;

import am.eua.distrcomp.warehouse.data.Product;
import am.eua.distrcomp.warehouse.services.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public class WarehouseListener {
    @Autowired
    private ProductService productService;


    private final ObjectMapper mapper = new ObjectMapper();


    @RabbitListener(queues = "requests")
    public void onMessage(String message) {
        // The return value is sent back as the reply to the Shop via RabbitMQ
        try {
            Map<String, Object> request = mapper.readValue(message, new TypeReference<Map<String, Object>>() {});
            String operation = (String) request.get("operation");

            if ("listProducts".equals(operation)) {
                List<Product> products = productService.findAll();
                System.out.println("Returned products list");
            }
            else {
                System.out.println("Unknown operation: " + operation);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Return the error as a string so the Shop sees it

        }
    }
}

