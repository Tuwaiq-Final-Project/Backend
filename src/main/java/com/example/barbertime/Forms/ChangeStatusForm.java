package com.example.barbertime.Forms;

import javax.validation.constraints.NotBlank;

public class ChangeStatusForm {

    @NotBlank(message = "status is required")
    private String status;

    public ChangeStatusForm() {
    }

    public ChangeStatusForm(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
