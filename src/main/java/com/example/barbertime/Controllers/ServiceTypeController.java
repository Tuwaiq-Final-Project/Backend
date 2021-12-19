package com.example.barbertime.Controllers;

import com.example.barbertime.Entities.ServiceType;
import com.example.barbertime.Services.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("services")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;

    @GetMapping
    public List<ServiceType> getServices()
    {
        return serviceTypeService.getServices();
    }

    @PostMapping
    public ResponseEntity<?> saveService(@Valid @RequestBody ServiceType service)
    {
        return serviceTypeService.saveService(service);
    }


}
