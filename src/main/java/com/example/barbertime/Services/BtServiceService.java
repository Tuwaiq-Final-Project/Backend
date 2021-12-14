package com.example.barbertime.Services;

import com.example.barbertime.Entities.BtService;
import com.example.barbertime.Repositories.BtServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BtServiceService {

    @Autowired
    private BtServiceRepository  btServiceRepository;

    public List<BtService> getServices()
    {
        return btServiceRepository.findAll();
    }

    public ResponseEntity<?> saveService(BtService service)
    {
        return ResponseEntity.ok().body(btServiceRepository.save(service));
    }
}
