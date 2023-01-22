package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public interface RoleService {
    List<Role> getAllRoles();
    Role findRoleByName(String name);

    Set<Role> getSetOfRoles(List<String> rolesId);
}
