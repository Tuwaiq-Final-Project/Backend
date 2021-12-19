package com.example.barbertime.Forms;

import com.example.barbertime.Entities.User;
import javax.validation.constraints.NotNull;

public class CreateUserForm {
    private User user;
    @NotNull
    private Long role_id;

    public User getUser() {
        return user;
    }

    public Long getRole_id() {
        return role_id;
    }
}
