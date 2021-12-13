package com.example.barbertime.Repositories;

import com.example.barbertime.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
