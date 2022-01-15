package com.example.barbertime.Services;

import com.example.barbertime.Entities.User;
import com.example.barbertime.Forms.CreateUserForm;
import com.example.barbertime.Repositories.UserRepository;
import com.example.barbertime.Role.Role;
import com.example.barbertime.Role.RoleRepo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
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
        Map<String,String> returnCreateUser = new HashMap<>();

        try{
            // check if there a USER = 1 role in database
            if(roleRepo.findById(createUserForm.getRole_id()).orElse(null) == null)
            {
                returnCreateUser.put("error","There are no roles on DB");
                return ResponseEntity.status(404).body(returnCreateUser);
            }
            // check if the email is already used
            if(userRepository.findByEmail(createUserForm.getUser().getEmail()) != null)
            {
                returnCreateUser.put("email","The email already used");
            }

            // check if the phone is already used
            if(userRepository.findByPhone(createUserForm.getUser().getPhone()) != null)
            {
                returnCreateUser.put("phone","The Phone already used");
            }

            // check if the email or phone are already used
            if(!returnCreateUser.isEmpty())
            {
                return ResponseEntity.status(404).body(returnCreateUser);
            }

            User user = createUserForm.getUser();
            Long role_id = createUserForm.getRole_id();
            Role role = roleRepo.findById(role_id).orElse(null);
            user.getRoles().add(role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
            returnCreateUser.put("success","Account created successfully");
            return ResponseEntity.ok().body(returnCreateUser);

        }
        catch (Exception e)
        {
            returnCreateUser.put("error","General Exception");
            return ResponseEntity.status(404).body(returnCreateUser);
        }
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
