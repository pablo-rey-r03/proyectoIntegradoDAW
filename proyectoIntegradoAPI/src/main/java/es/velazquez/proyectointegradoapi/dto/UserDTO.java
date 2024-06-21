package es.velazquez.proyectointegradoapi.dto;

import es.velazquez.proyectointegradoapi.model.Role;

public class UserDTO {
    private String email;
    private String password;
    private Role role;

    public UserDTO(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
