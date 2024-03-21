package com.example.ManageMoney.controller;

import com.example.ManageMoney.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
@GetMapping("/mypage")
public String showMyPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model){
    model.addAttribute("user",userDetails.getUser());
    return "mypage";
}
}
