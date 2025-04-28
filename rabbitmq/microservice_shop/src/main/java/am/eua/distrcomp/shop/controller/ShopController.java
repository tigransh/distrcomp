package am.eua.distrcomp.shop.controller;

import am.eua.distrcomp.shop.data.Product;
import am.eua.distrcomp.shop.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Shop Controller", description = "API for managing shop orders")
public class ShopController {
    private final ShopService orderService;

    @Autowired
    public ShopController(ShopService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieve a list of all products in the shop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of orders",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)))
    })
    public List<Product> getAllProductsSync() {
        return orderService.findAllSync();
    }

    @GetMapping("/async")
    @Operation(summary = "Get all products", description = "Retrieve a list of all products in the shop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of orders",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)))
    })
    public void getAllProductsASync() {
        orderService.findAllAsync();
    }


}
