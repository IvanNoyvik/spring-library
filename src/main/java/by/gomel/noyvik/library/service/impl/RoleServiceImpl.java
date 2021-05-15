package by.gomel.noyvik.library.service.impl;

import by.gomel.noyvik.library.model.Role;
import by.gomel.noyvik.library.persistence.repository.RoleRepository;
import by.gomel.noyvik.library.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Role getRole(String role) {
        return roleRepository.findByRole(role);
    }
}
