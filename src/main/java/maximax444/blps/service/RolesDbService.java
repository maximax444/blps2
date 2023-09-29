package maximax444.blps.service;

import lombok.AllArgsConstructor;
import maximax444.blps.entity.Role;
import maximax444.blps.exceptions.EntityNotFoundException;
import maximax444.blps.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RolesDbService {
    private final String ENTITY_ROLE_CLASS_NAME = "Роль";
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_ROLE_CLASS_NAME));

    }
}