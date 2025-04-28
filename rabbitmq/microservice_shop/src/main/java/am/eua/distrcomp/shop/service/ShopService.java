package am.eua.distrcomp.shop.service;


import am.eua.distrcomp.shop.data.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ShopService {


    private final RestTemplate restTemplate;
    private static final String WAREHOUSE_URL = "http://localhost:8080/products";

    @Autowired
    public ShopService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    public List<Product> findAllSync() {
        // Call warehouse to get all orders
        return restTemplate.exchange(
                WAREHOUSE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        ).getBody();
    }



    @SuppressWarnings("unchecked")
    public void findAllAsync() {
        try {
            // Build request
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("operation", "listProducts");

            String requestJson = mapper.writeValueAsString(requestMap);

            // Send request, receive response
//            String responseJson = (String) rabbitTemplate.convertSendAndReceive("warehouseRequests", requestJson);
//            rabbitTemplate.convertAndSend("myQueue", requestJson);
            // Send it to warehouseExchange with routing key "warehouse.request"
            rabbitTemplate.convertAndSend(
                    "warehouseExchange",    // exchange
                    "warehouse.request",    // routing key
                    requestJson,            // message body
                    msg -> {
                        // Set correlation ID
                        msg.getMessageProperties().setCorrelationId(UUID.randomUUID().toString());
                        return msg;
                    }
            );

            // Convert response from JSON to a List<Product>
//            return mapper.readValue(responseJson, new TypeReference<List<Product>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }





}