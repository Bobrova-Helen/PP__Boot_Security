package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAdminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/add")
    public String newUserPage(@ModelAttribute ("user") User user, Model model) {
//        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "user-create";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("newUser") UserDTO userDTO, Model model) {
        User user=new User(
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getAge(),
                userDTO.getEmail(),
                userDTO.getLogin(),
                userDTO.getPassword(),
                (userDTO.getRoles().stream().map(S -> roleService.getRole(S)).collect(Collectors.toSet()))
        );
        System.out.println("info");
//        Arrays.stream(user.getRoleNames()).forEach(s -> System.out.println(s));
//        ModelAttribute возвращает 1 атрибут из всего списка
//        user.setRoles(Set.of(roleService.getRole(user.getRoleNames())));
        System.out.println(model.getAttribute("roleNames"));
        System.out.println(model.asMap());

        System.out.println("inf");
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PutMapping("/{id}/update")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        getUserRoles(user);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    private void getUserRoles(User user) {
        user.setRoles(user.getRoles().stream()
                .map(role -> roleService.getRole(role.getName()))
                .collect(Collectors.toSet()));
    }
}
