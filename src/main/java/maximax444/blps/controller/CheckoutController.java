package maximax444.blps.controller;

import lombok.AllArgsConstructor;
import maximax444.blps.dto.ProductDTO;
import maximax444.blps.entity.Order;
import maximax444.blps.entity.Product;
import maximax444.blps.security.MyResourceNotFoundException;
import maximax444.blps.service.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class CheckoutController {
    private final CustomerInterface customerService;
    private final OrderInterface orderService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Order createOrder(@RequestBody List<ProductDTO> productDTOs) {


        return orderService.addOrder(productDTOs, customerService.getUserFromContext());
    }
}
