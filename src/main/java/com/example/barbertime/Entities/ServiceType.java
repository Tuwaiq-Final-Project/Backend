package com.example.barbertime.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "services")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    @NotBlank(message = "type is required")
    private String type;

    @Column(name = "description",length = 512)
    @NotBlank(message = "message is required")
    @Size(max = 512)
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "period")
    private int period;

    @JsonIgnoreProperties("service")
    @OneToMany(mappedBy = "service")
    private List<Reservation> reservations;

    public ServiceType() {
    }

    public ServiceType(Long id, String type, String description, double price, List<Reservation> reservations) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.price = price;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
