package com.example.barbertime.Repositories;

import com.example.barbertime.Entities.BtService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BtServiceRepository extends JpaRepository<BtService,Long> {
}
