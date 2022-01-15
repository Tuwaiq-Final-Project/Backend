package com.example.barbertime.Services;

import com.example.barbertime.Controllers.ReservationController;
import com.example.barbertime.Entities.Reservation;
import com.example.barbertime.Entities.User;
import com.example.barbertime.Forms.ReservationRequestForm;
import com.example.barbertime.Repositories.ReservationRepository;
import com.example.barbertime.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.example.barbertime.Forms.ChangeStatusForm;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Reservation> getReservations()
    {
        return reservationRepository.findAll();
    }

    public ResponseEntity<?> createReservation(Reservation reservation)
    {
        // Note : before return : check if the user book for him self only ( by get the compare authentication id & the body request id).
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

    public ResponseEntity<?> reservationRequest(String id, ReservationRequestForm reservationRequestForm)
    {
        System.out.println("aa");

        Reservation reservationRequest = reservationRepository.findById(Long.parseLong(id)).orElse(null);

        if (reservationRequest != null)
        {
            User u =  userRepository.findById(Long.parseLong(reservationRequestForm.getUser())).orElse(null);
            reservationRequest.setUser(u);
            reservationRepository.save(reservationRequest);
            return ResponseEntity.ok().body("Request applied Successfully");
        }
        else{
            System.out.println("Not gg");
            return ResponseEntity.status(404).body("Reservation Not Found");
        }

    }

    public List<Reservation> getMyReservations(String id)
    {
        return reservationRepository.findByUser_id(Long.parseLong(id));
    }

    public ResponseEntity<?> checkIfTherePendingReservations(String id)
    {
        List<Reservation> myReservation = reservationRepository.findByUser_id(Long.parseLong(id));
        Map<String,Boolean> havePending = new HashMap();


        for (Reservation r :myReservation)
        {
            if(r.getStatus().equals("Pending"))
            {
                havePending.put("message",true);
                return ResponseEntity.status(200).body(havePending);
            }
        }

        havePending.put("message",false);
        return ResponseEntity.status(200).body(havePending);
    }
}
