package com.example.barbertime.Services;

import com.example.barbertime.Entities.ServiceType;
import com.example.barbertime.Repositories.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeService {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public List<ServiceType> getServices()
    {
        return serviceTypeRepository.findAll();
    }

    public ResponseEntity<?> saveService(ServiceType service)
    {
        return ResponseEntity.ok().body(serviceTypeRepository.save(service));
    }
}
