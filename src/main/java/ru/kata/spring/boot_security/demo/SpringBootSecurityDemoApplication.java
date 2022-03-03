package ru.kata.spring.boot_security.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
@AllArgsConstructor
@SpringBootApplication
public class SpringBootSecurityDemoApplication implements CommandLineRunner {

    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Role> roles1 = List.of(new Role("user"));
        User user1 = new User("Petr", "Petrov", "1", roles1);

        List<Role> roles2 = List.of(new Role("user"), new Role("admin"));
        User user2 = new User("Ivan", "Petrov", "1", roles2);
        userService.saveUser(user1);
        userService.saveUser(user2);
    }
}
