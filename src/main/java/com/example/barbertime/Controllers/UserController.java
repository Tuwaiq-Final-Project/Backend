package com.example.barbertime.Controllers;

import com.example.barbertime.Entities.User;
import com.example.barbertime.Forms.CreateUserForm;
import com.example.barbertime.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getUsers()
    {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id)
    {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserForm createUserForm)
    {
        return userService.createUser(createUserForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id ,@Valid @RequestBody User user)
    {
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id)
    {
        return userService.deleteUser(id);
    }

    // Specific Requests

    @GetMapping("/count")
    public long getUsersCount()
    {
        return userService.getUsersCount();
    }
}