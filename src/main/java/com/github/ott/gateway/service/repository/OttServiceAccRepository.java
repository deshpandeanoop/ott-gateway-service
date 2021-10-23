package com.github.ott.gateway.service.repository;

import com.github.ott.gateway.service.data.OttServiceAccount;
import com.github.ott.gateway.service.data.OttServiceAccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OttServiceAccRepository extends JpaRepository<OttServiceAccount, OttServiceAccountId> {
}
