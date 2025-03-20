package am.eua.distrcomp.shop.service;


import am.eua.distrcomp.shop.data.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service

public class OrderService {

    private final RestTemplate restTemplate;
    private static final String WAREHOUSE_URL = "http://localhost:8080/products";

    @Autowired
    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> findAll() {
        // Call warehouse to get all orders
        return restTemplate.exchange(
                WAREHOUSE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        ).getBody();
    }


}