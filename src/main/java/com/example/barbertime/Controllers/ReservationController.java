package com.example.barbertime.Controllers;

import com.example.barbertime.Entities.Reservation;
import com.example.barbertime.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.barbertime.Forms.ChangeStatusForm;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getReservations()
    {
        return reservationService.getReservations();
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@Valid @RequestBody Reservation reservation)
    {
        return reservationService.createReservation(reservation);
    }


    @PostMapping("/change-status/{id}")
    public ResponseEntity<?> changeStatusReservation(@PathVariable String id,@Valid @RequestBody ChangeStatusForm changeStatusForm)
    {
        return reservationService.changeStatusReservation(id,changeStatusForm);
    }

}
