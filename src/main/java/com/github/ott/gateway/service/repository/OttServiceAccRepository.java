package com.github.ott.gateway.service.repository;

import com.github.ott.gateway.service.data.db.OttServiceAccount;
import com.github.ott.gateway.service.data.db.OttServiceAccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OttServiceAccRepository extends JpaRepository<OttServiceAccount, OttServiceAccountId> {

    List<OttServiceAccount> getByGateWayServiceUserId(String gateWayUserId);
}
