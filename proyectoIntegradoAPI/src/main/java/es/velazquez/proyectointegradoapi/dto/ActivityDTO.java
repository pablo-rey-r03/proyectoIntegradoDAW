package es.velazquez.proyectointegradoapi.dto;

import java.util.Date;

public class ActivityDTO {
    private Long id;
    private String name;
    private String description;
    private Date date;
    private String location;
    private Float price;
    private String duration;
    private Integer maxQuota;
    private Integer availableQuota;
    private String requiredMats;
    private String providedMats;
    private String recommendedFormation;
    private Long offererId;

    public ActivityDTO(Long id, String name, String description, Date date, String location, Float price, String duration, Integer maxQuota, Integer availableQuota, String requiredMats, String providedMats, String recommendedFormation, Long offererId) {
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
        this.offererId = offererId;
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

    public Long getOffererId() {
        return offererId;
    }

    public void setOffererId(Long offererId) {
        this.offererId = offererId;
    }
}
