package maximax444.blps.security.jwt;

import lombok.AllArgsConstructor;
import maximax444.blps.dto.UserDTO;
import maximax444.blps.entity.Customer;
import maximax444.blps.service.CustomerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtService {
    private final CustomerService customerService;


    public UserDetails loadUserByUsername(String username) {
        Customer user = customerService.findByName(username);

        return JwtUserFactory.create(user);
    }
}
