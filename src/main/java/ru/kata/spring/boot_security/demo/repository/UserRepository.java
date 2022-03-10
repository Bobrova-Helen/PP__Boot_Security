package ru.kata.spring.boot_security.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    User findUserByFirstname(String username);

    User findUserById(Long id);
    User findUserByLogin(String username);

//    public void deleteUser(long id) {
//    }
//
//    public List<User> getAllUsers() {
//        return null;
//    }
//
//    public UserDetails getUserById(Long id) {
//        return null;
//    }
//
//    public UserDetails getUserByLogin(String login) {
//        return null;
//    }
//
//    public void saveUser(User user) {
//    }
//
    public default void updateUser(User user) {
        User u = findUserById(user.getId());
        u.setFirstname(user.getFirstname());
        save(u);
    }
    List<User> findAllByRoles(Role role);

}
