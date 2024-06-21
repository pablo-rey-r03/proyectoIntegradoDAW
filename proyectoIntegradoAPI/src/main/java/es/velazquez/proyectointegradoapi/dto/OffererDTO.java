package es.velazquez.proyectointegradoapi.dto;

import java.util.Date;

public class OffererDTO {
    @Override
    public String toString() {
        return "OffererDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", formation='" + formation + '\'' +
                ", user_email='" + user_email + '\'' +
                '}';
    }

    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private Date birthDate;
    private String formation;
    private String user_email;

    public OffererDTO(String name, String surname, String address, String phoneNumber, Date birthDate, String formation, String user_email) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.formation = formation;
        this.user_email = user_email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getUser() {
        return user_email;
    }

    public void setUser(String user) {
        this.user_email = user;
    }
}
