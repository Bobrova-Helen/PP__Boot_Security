package ru.kata.spring.boot_security.demo.service;

import lombok.AllArgsConstructor;
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

@AllArgsConstructor
@Service
public abstract class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;



    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);

    }

    public List<User> findAll() {
        return userRepository.findAll();

    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);

    }

    public User getUserByUsername(String username){
        return userRepository.findUserByLogin(username);
    }

    @Transactional
    public abstract void updateUser(User user);

    @Transactional
    public abstract void deleteUser(long id);

    public abstract List<User> getAllUsers();
    public abstract List<User> getAllAdmins();

    public abstract User getUserById(long id);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(username);
    }

    public Object findAllUsers() {
        return null;
    }
}
