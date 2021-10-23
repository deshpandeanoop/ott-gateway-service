package com.github.ott.gateway.service.data;

import com.github.ott.gateway.service.enums.OttPlatformType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class OttServiceAccountId implements Serializable {

    private String username;

    private OttPlatformType platformType;
}
