package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@AllArgsConstructor

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public String userInfo(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        model.addAttribute("user", user);
        return "user";
    }
}
