package es.velazquez.proyectointegradoapi.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private Integer maxQuota;

    @Column(nullable = false)
    private Integer availableQuota;

    @Column
    private String requiredMats;

    @Column
    private String providedMats;

    @Column
    private String recommendedFormation;

    @ManyToOne
    private Offerer offerer;

    public Activity() {
    }

    public Activity(Long id, String name, String description, Date date, String location, Float price, String duration, Integer maxQuota, Integer availableQuota, String requiredMats, String providedMats, String recommendedFormation, Offerer offerer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.price = price;
        this.duration = duration;
        this.maxQuota = maxQuota;
        this.availableQuota = availableQuota;
        this.requiredMats = requiredMats;
        this.providedMats = providedMats;
        this.recommendedFormation = recommendedFormation;
        this.offerer = offerer;
    }

    public Activity(String name, String description, Date date, String location, Float price, String duration, Integer maxQuota, Integer availableQuota, String requiredMats, String providedMats, String recommendedFormation, Offerer offerer) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.price = price;
        this.duration = duration;
        this.maxQuota = maxQuota;
        this.availableQuota = availableQuota;
        this.requiredMats = requiredMats;
        this.providedMats = providedMats;
        this.recommendedFormation = recommendedFormation;
        this.offerer = offerer;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getMaxQuota() {
        return maxQuota;
    }

    public void setMaxQuota(Integer maxQuota) {
        this.maxQuota = maxQuota;
    }

    public Integer getAvailableQuota() {
        return availableQuota;
    }

    public void setAvailableQuota(Integer availableQuota) {
        this.availableQuota = availableQuota;
    }

    public String getRequiredMats() {
        return requiredMats;
    }

    public void setRequiredMats(String requiredMats) {
        this.requiredMats = requiredMats;
    }

    public String getProvidedMats() {
        return providedMats;
    }

    public void setProvidedMats(String providedMats) {
        this.providedMats = providedMats;
    }

    public String getRecommendedFormation() {
        return recommendedFormation;
    }

    public void setRecommendedFormation(String recommendedFormation) {
        this.recommendedFormation = recommendedFormation;
    }

    public Offerer getOfferer() {
        return offerer;
    }

    public void setOfferer(Offerer offerer) {
        this.offerer = offerer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity activity)) return false;
        return Objects.equals(getId(), activity.getId()) && Objects.equals(getName(), activity.getName()) && Objects.equals(getDescription(), activity.getDescription()) && Objects.equals(getDate(), activity.getDate()) && Objects.equals(getLocation(), activity.getLocation()) && Objects.equals(getPrice(), activity.getPrice()) && Objects.equals(getDuration(), activity.getDuration()) && Objects.equals(getMaxQuota(), activity.getMaxQuota()) && Objects.equals(getAvailableQuota(), activity.getAvailableQuota()) && Objects.equals(getRequiredMats(), activity.getRequiredMats()) && Objects.equals(getProvidedMats(), activity.getProvidedMats()) && Objects.equals(getRecommendedFormation(), activity.getRecommendedFormation()) && Objects.equals(getOfferer(), activity.getOfferer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getDate(), getLocation(), getPrice(), getDuration(), getMaxQuota(), getAvailableQuota(), getRequiredMats(), getProvidedMats(), getRecommendedFormation(), getOfferer());
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", duration='" + duration + '\'' +
                ", maxQuota=" + maxQuota +
                ", availableQuota=" + availableQuota +
                ", requiredMats='" + requiredMats + '\'' +
                ", providedMats='" + providedMats + '\'' +
                ", recommendedFormation='" + recommendedFormation + '\'' +
                ", offerer=" + offerer +
                '}';
    }
}
