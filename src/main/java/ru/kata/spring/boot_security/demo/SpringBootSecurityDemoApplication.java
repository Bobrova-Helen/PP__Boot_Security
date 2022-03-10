package ru.kata.spring.boot_security.demo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@SpringBootApplication
public class SpringBootSecurityDemoApplication implements CommandLineRunner {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role user=new Role("user");
        Role admin=new Role("admin");
        roleService.addRole(user);
        roleService.addRole(admin);
        Set<Role> roles1 = Set.of(user);


        Set<Role> roles2 = Set.of(user, admin);
        userService.saveUser(new User("user").setRoles(roles1));
        userService.saveUser(new User("admin").setRoles(roles2));
    }
}
