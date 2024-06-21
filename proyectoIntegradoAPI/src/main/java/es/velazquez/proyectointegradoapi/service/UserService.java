package es.velazquez.proyectointegradoapi.service;

import es.velazquez.proyectointegradoapi.model.User;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User saveUser(User user);
    void deleteUser(String email);
    boolean existsByEmail(String email);
}
