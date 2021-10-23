package com.github.ott.gateway.service.data.db;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class PlatformUserId implements Serializable {

    private String username;
}
