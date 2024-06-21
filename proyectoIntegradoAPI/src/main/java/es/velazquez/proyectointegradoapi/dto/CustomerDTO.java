package es.velazquez.proyectointegradoapi.dto;

import java.util.Date;

public class CustomerDTO {
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private Date birthDate;
    private String user_email;

    public CustomerDTO(String name, String surname, String address, String phoneNumber, Date birthDate, String user_email) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
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

    public String getUserEmail() {
        return user_email;
    }

    public void setUser(String user_email) {
        this.user_email = user_email;
    }
}
