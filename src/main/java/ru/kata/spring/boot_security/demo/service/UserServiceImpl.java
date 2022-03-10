package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl extends UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    //
//
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, PasswordEncoder passwordEncoder1, RoleRepository roleRepository) {
        super(userRepository, passwordEncoder, roleRepository);
        this.passwordEncoder = passwordEncoder1;
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
    }

    //
//
//    @Override
//    @Transactional
    //public User saveUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.saveUser(user);
//        return user;
//    }
//
    @Override
    @Transactional
    public void updateUser(User user) {
        if (!user.getPassword().equals(userRepository.findUserById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.updateUser(user);
    }

    //
    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.delete(userRepository.findUserById(id));
    }

    //
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllAdmins() {
        return userRepository.findAllByRoles(
                roleRepository.findByName("admin")
        );
    }


    //
    @Override
    public User getUserById(long id) {
        return (User) userRepository.findUserById(id);
    }
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        return userRepository.getUserByLogin(login);
//    }
}