package com.example.barbertime.Repositories;

import com.example.barbertime.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByUser_id(long parseLong);
    List<Reservation> findByDate(LocalDate parseLong); // not used
    Reservation findByDateAndTime(LocalDate date , LocalTime time);

}
