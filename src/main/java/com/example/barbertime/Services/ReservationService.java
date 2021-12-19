package com.example.barbertime.Services;

import com.example.barbertime.Controllers.ReservationController;
import com.example.barbertime.Entities.Reservation;
import com.example.barbertime.Entities.User;
import com.example.barbertime.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        // for check if the user book for him self only
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();

//        System.out.println("authentication user id : " + user.getId() ) ;
//        System.out.println("reservation user id : " + reservation.getUser().getId()) ;
//        if(user.getId() !=  reservation.getUser().getId())
//        {
//            return ResponseEntity.status(403).body("You are not Authorize")
//        }
//        else
//        {
//            return ResponseEntity.ok().body(reservationRepository.save(reservation));
//        }
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
