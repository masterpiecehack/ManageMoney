package com.example.ManageMoney.service;

import com.example.ManageMoney.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithDrawService {
    @Autowired
    UsersRepository usersRepository;

    public void deleteById(Long userId) {
        usersRepository.deleteById(userId);
    }
}
