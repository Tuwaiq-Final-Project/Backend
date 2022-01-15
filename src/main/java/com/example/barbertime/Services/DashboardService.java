package com.example.barbertime.Services;

import com.example.barbertime.Entities.User;
import com.example.barbertime.Repositories.MessageRepository;
import com.example.barbertime.Repositories.ReservationRepository;
import com.example.barbertime.Repositories.ServiceTypeRepository;
import com.example.barbertime.Repositories.UserRepository;
import com.example.barbertime.Role.Role;
import com.example.barbertime.Role.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private MessageRepository messageRepository;


    public Map<String,Integer> getAllData()
    {
        int admins = 0 ;
        int users = 0;
        List<User> allUsers = userRepository.findAll();
        for(User user : allUsers)
        {
            List<Role> roles =  user.getRoles();
            for(Role role : roles)
            {
                if(role.getName().equals("ADMIN"))
                {
                    admins++;
                }
                else{
                    users++;
                }
            }
        }
        
        Map<String ,Integer> dataMap = new HashMap<>();
        dataMap.put("users",users);
        dataMap.put("admins",admins);
        dataMap.put("services",serviceTypeRepository.findAll().size());
        dataMap.put("reservations",reservationRepository.findAll().size());
        dataMap.put("messages",messageRepository.findAll().size());
        return dataMap;
    }
}
