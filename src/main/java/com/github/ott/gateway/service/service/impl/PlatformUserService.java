package com.github.ott.gateway.service.service.impl;

import com.github.ott.gateway.service.data.db.PlatformUser;
import com.github.ott.gateway.service.data.db.UserAccount;
import com.github.ott.gateway.service.repository.PlatformUserRepository;
import com.github.ott.gateway.service.service.EnDecryptionService;
import com.github.ott.gateway.service.service.IPlatformUserService;
import com.github.ott.gateway.service.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("platformUserService")
@RequiredArgsConstructor
@Slf4j
public class PlatformUserService implements UserDetailsService, IPlatformUserService{

    private final PlatformUserRepository platformUserRepository;
    private final JwtUtils jwtUtils;
    private final EnDecryptionService enDecryptionService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.debug("Loading user details for the platform user with name : {}", userName);
        PlatformUser platformUser = getDecryptedPlatformUser(platformUserRepository.getByUsername(userName));
        return new User(platformUser.getUsername(), platformUser.getPassword(), new ArrayList<>());
    }

    public String generateJwtToken(UserAccount userAccount) {
        return jwtUtils.generateToken(userAccount);
    }

    @Override
    public PlatformUser save(PlatformUser platformUser) {
        PlatformUser encryptedPlatformUser = getEncryptedPlatformUser(platformUser);
        return platformUserRepository.save(encryptedPlatformUser);
    }

    private PlatformUser getEncryptedPlatformUser(PlatformUser platformUser) {
        return new PlatformUser(
              platformUser.getUsername(),
              enDecryptionService.encrypt(platformUser.getPassword()),
                platformUser.getFirstName(),
                platformUser.getLastName());
    }

    private PlatformUser getDecryptedPlatformUser(PlatformUser platformUser) {
        return new PlatformUser(
                platformUser.getUsername(),
                enDecryptionService.decrypt(platformUser.getPassword()),
                platformUser.getFirstName(),
                platformUser.getLastName());
    }
}
