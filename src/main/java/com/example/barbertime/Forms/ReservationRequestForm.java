package com.example.barbertime.Forms;

import com.example.barbertime.Entities.User;

public class ReservationRequestForm {


    private String user;

    public ReservationRequestForm() {
    }

    public ReservationRequestForm(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
