package com.github.ott.gateway.service.data.db;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A general base class for holding username & password
 */
@MappedSuperclass
@Getter
@EqualsAndHashCode
public class UserAccount implements Serializable {

    @Id
    private String username;
    private String password;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserAccount() {
    }
}
