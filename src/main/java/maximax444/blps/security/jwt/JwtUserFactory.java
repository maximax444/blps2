package maximax444.blps.security.jwt;

import maximax444.blps.dto.UserDTO;
import maximax444.blps.entity.Customer;
import maximax444.blps.service.RolesService;

public final class JwtUserFactory {
    public static JwtUser create(Customer user) {
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                RolesService.createGrantedAuthorities(user.getRole())
        );
    }
}