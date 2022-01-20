package com.example.barbertime.Controllers;

import com.example.barbertime.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("all")
    public Map<String,Integer> getAllData()
    {
        return dashboardService.getAllData();
    }

    @GetMapping("get-users-messages")
    public Map<String,Integer> getUsersMessages()
    {
        return dashboardService.getUsersMessages();
    }
}
