package com.example.ManageMoney.controller;

import com.example.ManageMoney.entity.Users;
import com.example.ManageMoney.form.SignupForm;
import com.example.ManageMoney.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processRegistration(@ModelAttribute SignupForm signupForm) {
        Users user = new Users();
        user.setUsername(signupForm.getUsername());
        user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        user.setEmail(signupForm.getEmail());
        usersRepository.save(user);
        return "redirect:/login";
    }
}
