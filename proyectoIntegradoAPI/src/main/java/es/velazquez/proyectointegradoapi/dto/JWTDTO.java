package es.velazquez.proyectointegradoapi.dto;

import java.util.Objects;

public class JWTDTO {
    private String token;

    public JWTDTO() {
    }

    public JWTDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JWTDTO jwtdto)) return false;
        return Objects.equals(getToken(), jwtdto.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken());
    }
}
