package com.myproject.StayBuddy.repositories;

import com.myproject.StayBuddy.entities.Booking;
import com.myproject.StayBuddy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
