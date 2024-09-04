package com.example.otp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.otp.Model.userModel;

public interface UserRepository extends JpaRepository<userModel, Long> {
    userModel findByEmail(String email);

}
