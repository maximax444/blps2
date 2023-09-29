package maximax444.blps.controller;

import maximax444.blps.dto.ProductDTO;
import maximax444.blps.entity.Product;
import maximax444.blps.entity.Customer;
import maximax444.blps.security.MyResourceNotFoundException;
import maximax444.blps.service.CustomerInterface;
import maximax444.blps.service.CustomerService;
import maximax444.blps.service.ProductInterface;
import maximax444.blps.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserProfileController {

    private final CustomerInterface customerService;
    private final ProductInterface productService;

//    @DeleteMapping(value = "/users/delete/me")
//    public String deleteMe(HttpServletRequest httpServletRequest) {
//        customerService.deleteMe(httpServletRequest);
//        return "Вы успешно удалены из системы";
//    }

    @GetMapping(value = "/products/all", produces = "application/json")
    public List<Product> findAllApprovedApartments() {
        return productService.findAllReadyProducts();
    }




}
