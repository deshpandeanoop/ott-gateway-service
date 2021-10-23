package com.github.ott.gateway.service.controller;

import com.github.ott.gateway.service.data.db.PlatformUser;
import com.github.ott.gateway.service.data.db.UserAccount;
import com.github.ott.gateway.service.data.request.RegisterPlatformUserRequest;
import com.github.ott.gateway.service.repository.PlatformUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class PlatformServiceController {

    @Autowired
    private PlatformUserRepository platformUserRepository;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody RegisterPlatformUserRequest platformUserRequest) {
      platformUserRepository.save(platformUserRequest.getUserDetails());
      return ResponseEntity.ok("Success!");
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateUser(@RequestBody UserAccount userAccount) {
        PlatformUser user = platformUserRepository.getByUsername(userAccount.getUsername());
        if(null == user) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User!");
        }
        return ResponseEntity.ok("Success!");
    }
}
