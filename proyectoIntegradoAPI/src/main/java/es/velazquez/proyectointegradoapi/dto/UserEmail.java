package es.velazquez.proyectointegradoapi.dto;

import java.util.Objects;

public class UserEmail {
    private String email;

    public UserEmail() {
    }

    public UserEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEmail userEmail1)) return false;
        return Objects.equals(getEmail(), userEmail1.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    @Override
    public String toString() {
        return "UserEmail{" +
                "userEmail='" + email + '\'' +
                '}';
    }
}
