package com.example.barbertime.Services;

import com.example.barbertime.Entities.User;
import com.example.barbertime.Forms.CreateUserForm;
import com.example.barbertime.Repositories.UserRepository;
import com.example.barbertime.Role.Role;
import com.example.barbertime.Role.RoleRepo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(email);
        if(user  == null){
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    // for log in the terminal server
//    private final Log logger = LogFactory.getLog(UserService.class);

    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    public ResponseEntity<?> getUser(String id)
    {
        // log message
//        logger.info(" ################# getUser #############");
        long user_id = Long.parseLong(id);
        return ResponseEntity.ok().body(userRepository.findById(user_id).orElse(null));
    }

    public ResponseEntity<?> createUser(CreateUserForm createUserForm)
    {
        User user = createUserForm.getUser();
        Long role_id = createUserForm.getRole_id();
        Role role = roleRepo.findById(role_id).orElse(null);
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
