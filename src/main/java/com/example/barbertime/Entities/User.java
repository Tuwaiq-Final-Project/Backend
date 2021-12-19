package com.example.barbertime.Entities;

import com.example.barbertime.Role.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
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

    @Column(name = "email", unique = true)
    @NotBlank(message = "email is required")
    @Email(message = "Must add @ sign")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "password is required")
    @Size(min = 8, message = "Password must be more than 8 characters!")
    private String password;

    @Column(name = "phone", unique = true)
    @NotBlank(message = "phone is required")
    @Size(min = 10, max = 15 ,message = "phone must be between 10 and 15 characters!")
    private String phone;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, String email, String password, String phone, List<Reservation> reservations, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.reservations = reservations;
        this.roles = roles;
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
