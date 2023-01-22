package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Set<Role> getSetOfRoles(List<String> rolesId) {
        Set<Role> roleSet = new HashSet<>();
        for (String id: rolesId) {
            roleSet.add(roleRepository.getById(Long.parseLong(id)));
        }
        return roleSet;
    }
}
