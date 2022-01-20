package com.example.barbertime.Services;

import com.example.barbertime.Entities.Reservation;
import com.example.barbertime.OtherClasses.AvailableDaysTimes;
import com.example.barbertime.Repositories.ReservationRepository;
import com.example.barbertime.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.barbertime.Forms.ChangeStatusForm;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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

    public ResponseEntity<?> getAvailableDaysTimes()
    {
        List<AvailableDaysTimes> gg = new ArrayList<>();
        String []times = {"13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00"};
        LocalDate Copyday = LocalDate.now();

        for(long i=1 ; i <=7 ;i++)
        {

            ArrayList<String> AvailableTimesAfterFilters = getAvailableTimes(Copyday,times);

            AvailableDaysTimes a1 = new AvailableDaysTimes(Copyday.getDayOfWeek().toString(),Copyday.toString(),AvailableTimesAfterFilters);


            gg.add(a1);
            Copyday = Copyday.plusDays(1);

        }

        return ResponseEntity.ok().body(gg);
    }

    private ArrayList<String> getAvailableTimes(LocalDate  day , String []times)
    {
        ArrayList<String> newArrrr = new ArrayList<>();

        for (String s:times)
        {
            if(reservationRepository.findByDateAndTime(day, LocalTime.parse(s)) == null)
            {
                newArrrr.add(s);
            }
        }
        return newArrrr;
    }
}
