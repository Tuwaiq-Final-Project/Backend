package com.example.barbertime.Services;

import com.example.barbertime.Controllers.ReservationController;
import com.example.barbertime.Entities.Reservation;
import com.example.barbertime.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.barbertime.Forms.ChangeStatusForm;


import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


    public List<Reservation> getReservations()
    {
        return reservationRepository.findAll();
    }

    public ResponseEntity<?> createReservation(Reservation reservation)
    {
        return ResponseEntity.ok().body(reservationRepository.save(reservation));
    }


    public ResponseEntity<?> changeStatusReservation(String id,  ChangeStatusForm changeStatusForm)
    {
        Reservation updateReservation = reservationRepository.findById(Long.parseLong(id)).orElse(null);

        if (updateReservation != null)
        {
            updateReservation.setStatus(changeStatusForm.getStatus());
            reservationRepository.save(updateReservation);
            return ResponseEntity.ok().body("Updated Successfully");
        }
        else{
            return ResponseEntity.status(404).body("Reservation Not Found");
        }
    }
}
