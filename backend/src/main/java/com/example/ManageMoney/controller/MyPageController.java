package com.example.ManageMoney.controller;

import com.example.ManageMoney.entity.Users;
import com.example.ManageMoney.repository.UsersRepository;
import com.example.ManageMoney.security.CustomUserDetails;
import com.example.ManageMoney.service.CustomUserDetailsService;
import com.example.ManageMoney.service.WithDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MyPageController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    WithDrawService withDrawService;

    @GetMapping("/mypage")
    public String showMyPage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUser());
        return "mypage";
    }

    @GetMapping("/updateAccount")
    public String showUpdateAccount(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Optional<Users> users = usersRepository.findById(userDetails.getUser().getId());
        model.addAttribute("user", users.isPresent() ? users.get() : "");
        return "updateaccount";
    }

    @PostMapping("/updateAccount")
    public String updateAccount(@AuthenticationPrincipal CustomUserDetails userDetails, Model model, @RequestParam("email") String newEmail, @RequestParam("current-password") String currentPassword, @RequestParam("new-password") String newPassword) {

        Users user = usersRepository.getById(userDetails.getUser().getId());
        model.addAttribute("user", userDetails.getUser());

        //新しいメールアドレスがすでにUsersテーブルの他のレコードにある場合は許可しない
        if (customUserDetailsService.emailExists(newPassword) && user.getEmail().equals(newEmail)) {
            model.addAttribute("error", "このメールアドレスは既に使用されています。");
            return "updateaccount";
        }

        //入力された現在のパスワードがハッシュ化されたパスワードと一致しない場合は許可しない
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            model.addAttribute("error", "現在のパスワードが正しくありません。");
            return "updateaccount";
        }

        //上記に該当しない場合はidが一致するUsersテーブルのレコードを更新する
        customUserDetailsService.updateUser(user.getId(), newEmail, passwordEncoder.encode(newPassword));

        //アップデートが完了したことを自分の画面の先頭にメッセ―ジ表示する
        model.addAttribute("message", "アカウント情報が更新されました。");
        return "mypage";
    }

    @PostMapping("/withdraw")
    public String withDraw(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        withDrawService.deleteById(customUserDetails.getUser().getId());
        return "redirect:/logout";
    }
}
