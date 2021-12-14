package com.example.barbertime.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "name is required")
    @Size(min = 2, message = "name must be more than 2 characters!")
    private String name;

    @Column(name = "email")
    @NotBlank(message = "email is required")
    @Email(message = "Must add @ sign")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "password is required")
    @Size(min = 3, message = "Password must be more than 3 characters!")
    private String password;

    @Column(name = "phone")
    @NotBlank(message = "phone is required")
    @Size(min = 10, max = 15 ,message = "phone must be between 10 and 15 characters!")
    private String phone;

    @Column(name = "role")
    @NotBlank(message = "role is required")
    private String role;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;


    public User() {
    }

    public User(Long id, String name, String email, String password, String phone, String role, List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.reservations = reservations;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}