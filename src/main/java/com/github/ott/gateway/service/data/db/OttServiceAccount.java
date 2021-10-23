package com.github.ott.gateway.service.data.db;

import com.github.ott.gateway.service.enums.OttPlatformType;
import lombok.Getter;

import javax.persistence.*;

/**
 * Holds the OTT account(Amazon Prime, Netflix) details of the user.
 */
@Entity
@IdClass(OttServiceAccountId.class)
@Getter
public class OttServiceAccount extends UserAccount {

    public OttServiceAccount(String username, String password, OttPlatformType platformType, String gateWayServiceUserId) {
        super(username, password);
        this.platformType = platformType;
        this.gateWayServiceUserId = gateWayServiceUserId;
    }

    public OttServiceAccount() {
    }

    @Id
    private OttPlatformType platformType;

    private String gateWayServiceUserId;
}