package com.example.barbertime.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "date")
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;


    @Column(name = "status")
    @NotBlank(message = "status is required")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("reservations")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id")
    @JsonIgnoreProperties("reservations")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private ServiceType service;




    public Reservation() {
    }

    public Reservation(Long id, LocalDate date, LocalTime time, String status, User user, ServiceType service) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.status = status;
        this.user = user;
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceType getService() {
        return service;
    }

    public void setService(ServiceType service) {
        this.service = service;
    }
}
