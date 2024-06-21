package es.velazquez.proyectointegradoapi.dto;

import java.util.Date;

public class RequestDTO {
    private String name;
    private String description;
    private Date date;
    private String location;
    private Long customerId;

    public RequestDTO(String name, String description, Date date, String location, Long customerId) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.customerId = customerId;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
