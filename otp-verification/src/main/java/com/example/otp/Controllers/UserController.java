package com.example.otp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.otp.Model.userModel;
import com.example.otp.Service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;  // Autowire the UserService
    
    @RequestMapping("/")
    public String index(){
        return "redirect:/register";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new userModel());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute userModel user, Model model) {
        System.out.println(user);
        userModel registeredUser = userService.registerUser(user);
        model.addAttribute("email", registeredUser.getEmail());
        return "verify";
    }

    @PostMapping("/verify")
    public String verifyUser(@RequestParam String email, @RequestParam String otp, Model model) {
        boolean isVerified = userService.verifyUser(email, otp);

        if (isVerified) {
            return "success";
        } else {
            model.addAttribute("error", "Invalid OTP");
            return "verify";
        }
    }
}
