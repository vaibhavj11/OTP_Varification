package com.example.otp.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.otp.Model.userModel;
import com.example.otp.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;
    
    public userModel registerUser(userModel user) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        user.setOtp(otp);
        user.setVerified(false);
        userModel savedUser = userRepository.save(user);
        sendOtpEmail(savedUser.getEmail(), otp);
        return savedUser;
    }
    
    private void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your OTP for Verification");
        message.setText("Dear User,\n\nYour OTP for registration is: " + otp + "\n\nThank you!");
        mailSender.send(message);
    }
    
    public boolean verifyUser(String email, String otp) {
        userModel user = userRepository.findByEmail(email);
        if (user != null && user.getOtp().equals(otp)) {
            user.setVerified(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
