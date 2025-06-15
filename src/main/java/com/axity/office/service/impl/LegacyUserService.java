package com.example.legacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LegacyUserService {

    @Autowired
    private LegacyUserRepository userRepository;

    public String getUserStatusMessage(int userId) {
        Optional<LegacyUser> userOpt = userRepository.findById(userId);

        if (userOpt.isPresent()) {
            LegacyUser user = userOpt.get();
            String status = user.getStatus();

            switch (status) {
                case "ACTIVE":
                    return "User is active";
                case "INACTIVE":
                    return "User is inactive";
                case "BANNED":
                    return "User is banned";
                default:
                    return "Unknown status";
            }
        } else {
            return "User not found";
        }
    }

    public void printWelcomeMessage() {
        String message = "Welcome to our system.\n"
                       + "Please read the terms and conditions carefully before proceeding.\n"
                       + "Contact support@example.com for assistance.";

        System.out.println(message);
    }
}