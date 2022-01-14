package com.github.ott.gateway.service.controller;

import com.github.ott.gateway.service.data.db.UserAccount;
import com.github.ott.gateway.service.data.request.RegisterPlatformUserRequest;
import com.github.ott.gateway.service.data.response.AuthResponse;
import com.github.ott.gateway.service.service.IPlatformUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@Slf4j
public class PlatformServiceController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IPlatformUserService platformUserService;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody RegisterPlatformUserRequest platformUserRequest) {
      platformUserService.save(platformUserRequest.getUserDetails());
      return ResponseEntity.ok("Success!");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> validateUser(@RequestBody UserAccount userAccount) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAccount.getUsername(), userAccount.getPassword()));
        } catch (BadCredentialsException badCredException) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials!");
        }
        return ResponseEntity.ok(new AuthResponse(platformUserService.generateJwtToken(userAccount)));
    }
}
