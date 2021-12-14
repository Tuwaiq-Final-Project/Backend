package com.example.barbertime.Controllers;

import com.example.barbertime.Entities.BtService;
import com.example.barbertime.Services.BtServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("services")
public class BtServiceController {

    @Autowired
    private BtServiceService btServiceService;

    @GetMapping
    public List<BtService> getServices()
    {
        return btServiceService.getServices();
    }

    @PostMapping
    public ResponseEntity<?> saveService(@RequestBody BtService service)
    {
        return btServiceService.saveService(service);
    }


}
