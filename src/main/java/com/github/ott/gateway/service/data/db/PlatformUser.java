package com.github.ott.gateway.service.data.db;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.IdClass;

/**
 * Details of the user who signs up on gateway platform
 */
@Getter
@Entity
@IdClass(PlatformUserId.class)
public class PlatformUser extends UserAccount {

    private String firstName;
    private String lastName;

    public PlatformUser(String username, String password, String firstName, String lastName) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PlatformUser() {
    }
}