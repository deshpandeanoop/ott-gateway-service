package com.github.ott.gateway.service.runner;

import com.github.ott.gateway.service.data.OttServiceAccount;
import com.github.ott.gateway.service.data.PlatformUser;
import com.github.ott.gateway.service.enums.OttPlatformType;
import com.github.ott.gateway.service.repository.OttServiceAccRepository;
import com.github.ott.gateway.service.repository.PlatformUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RepositoryRunner implements CommandLineRunner {

    @Autowired
    private PlatformUserRepository platformUserRepository;
    @Autowired
    private OttServiceAccRepository ottServiceAccRepository;

    @Override
    public void run(String... args) throws Exception {
        //String username, String password, String firstName, String lastName
        PlatformUser platformUser = new PlatformUser("u1", "p1", "f1", "l1");
        platformUserRepository.save(platformUser);

        //String username, String password, OttPlatformType platformType, String gateWayServiceUserId
        OttServiceAccount serviceAccount = new OttServiceAccount("u1", "p1", OttPlatformType.AMAZON_PRIME, "123");
        ottServiceAccRepository.save(serviceAccount);
    }
}
