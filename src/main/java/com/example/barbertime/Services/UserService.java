package com.example.barbertime.Services;

import com.example.barbertime.Entities.User;
import com.example.barbertime.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    public ResponseEntity<?> getUser(String id)
    {
        long user_id = Long.parseLong(id);
        return ResponseEntity.ok().body(userRepository.findById(user_id).orElse(null));
    }

    public ResponseEntity<?> createUser(User user)
    {
        return ResponseEntity.ok().body(userRepository.save(user));
    }

    public ResponseEntity<?> updateUser(String id, User updatedData)
    {
        Long user_id = Long.parseLong(id);
        User user = userRepository.findById(user_id).orElse(null);
        if (user != null)
        {
            user.setName(updatedData.getName());
            user.setEmail(updatedData.getEmail());
            user.setPassword(updatedData.getPassword());
            user.setPhone(updatedData.getPhone());
            user.setRole(updatedData.getRole());
            userRepository.save(user);
            return ResponseEntity.ok().body(userRepository.save(user));
        }
        else {
            return ResponseEntity.status(404).body("User Not Found");
        }
    }

    public ResponseEntity<?> deleteUser(String id)
    {
        try {
            Long user_id = Long.parseLong(id);
            userRepository.deleteById(user_id);
            return ResponseEntity.ok().body("User Deleted");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    public long getUsersCount()
    {
        return userRepository.count();
    }

}
