package es.velazquez.proyectointegradoapi.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Offerer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;

    @Column
    private String formation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_email", referencedColumnName = "email", unique = true)
    private User user;

    @OneToMany(
            mappedBy = "offerer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Activity> activities = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void removeUser() {
        this.user = null;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
        activity.setOfferer(this);
    }

    public void removeActivity(Activity activity) {
        this.activities.remove(activity);
        activity.setOfferer(null);
    }

    public Offerer() {
    }

    public Offerer(Long id, String name, String surname, String address, String phoneNumber, Date birthDate, String formation, User user, Set<Activity> activities) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.formation = formation;
        this.user = user;
        this.activities = activities;
    }

    public Offerer(String name, String surname, String address, String phoneNumber, Date birthDate, String formation, User user, Set<Activity> activities) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.formation = formation;
        this.user = user;
        this.activities = activities;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offerer offerer)) return false;
        return Objects.equals(getId(), offerer.getId()) && Objects.equals(getName(), offerer.getName()) && Objects.equals(getSurname(), offerer.getSurname()) && Objects.equals(getAddress(), offerer.getAddress()) && Objects.equals(getPhoneNumber(), offerer.getPhoneNumber()) && Objects.equals(getBirthDate(), offerer.getBirthDate()) && Objects.equals(getFormation(), offerer.getFormation()) && Objects.equals(getUser(), offerer.getUser()) && Objects.equals(getActivities(), offerer.getActivities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getAddress(), getPhoneNumber(), getBirthDate(), getFormation(), getUser(), getActivities());
    }

    @Override
    public String toString() {
        return "Offerer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate=" + birthDate +
                ", formation='" + formation + '\'' +
                ", user=" + user +
                ", activities=" + activities +
                '}';
    }
}
