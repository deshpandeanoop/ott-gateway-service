package com.github.ott.gateway.service.repository;

import com.github.ott.gateway.service.data.db.PlatformUser;
import com.github.ott.gateway.service.data.db.PlatformUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformUserRepository extends JpaRepository<PlatformUser, PlatformUserId> {

    PlatformUser getByUsername(String username);
}
