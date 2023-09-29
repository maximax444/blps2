package maximax444.blps.service;

import maximax444.blps.dto.CustomerDTO;
import maximax444.blps.entity.Customer;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface CustomerInterface {
    String login(String username, String password);
    Customer findByName(String userName);
    String registration(String username, String password);
    List<Customer> findAll();
    Customer getUserFromContext();
}
