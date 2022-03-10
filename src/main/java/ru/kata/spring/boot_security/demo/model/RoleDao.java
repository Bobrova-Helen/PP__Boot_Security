package ru.kata.spring.boot_security.demo.model;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();

    Role getRole(String userRole);

    Role getRoleById(Long id);

    void addRole(Role role);
}
