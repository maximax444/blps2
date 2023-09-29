package maximax444.blps.service;

import lombok.AllArgsConstructor;
import maximax444.blps.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RolesService {
    private final String DEFAULT_ROLE_NAME = "ROLE_USER";
    private final RolesDbService roleDbService;


    public String getRoleName(Role userRole) {
        String result = userRole.getName();

        return result;
    }

    public List<Role> getDefaultRoles() {
        Role roleUser = roleDbService.findByName(DEFAULT_ROLE_NAME);
        return List.of(roleUser);
    }

    public static List<GrantedAuthority> createGrantedAuthorities(Role userRole) {
        List<GrantedAuthority> ga = new ArrayList<>();
                ga.add(new SimpleGrantedAuthority(userRole.getName()));
        return ga;
    }
}