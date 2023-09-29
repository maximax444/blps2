package maximax444.blps.controller;

import lombok.AllArgsConstructor;
import maximax444.blps.dto.ProductDTO;
import maximax444.blps.entity.Customer;
import maximax444.blps.entity.Product;
import maximax444.blps.service.CustomerInterface;
import maximax444.blps.service.ProductInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final CustomerInterface customerService;
    private final ProductInterface productService;

    @GetMapping(value = "/users/user/all", produces = "application/json")
    public List<Customer> getAllUsers() {
        return customerService.findAll();
    }
    @PostMapping(value = "/products/create", produces = "application/json")
    public Product createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO, customerService.getUserFromContext());
    }
}
