package com.github.ott.gateway.service.data.request;

import com.github.ott.gateway.service.data.db.PlatformUser;
import lombok.Data;

@Data
public class RegisterPlatformUserRequest {

    private PlatformUser  userDetails;
}
