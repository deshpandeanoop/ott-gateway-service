package com.github.ott.gateway.service.service;

import com.github.ott.gateway.service.data.db.PlatformUser;
import com.github.ott.gateway.service.data.db.UserAccount;

public interface IPlatformUserService {

    String generateJwtToken(UserAccount userAccount);

    PlatformUser save(PlatformUser platformUser);
}
