package com.github.ott.gateway.service.service;

import com.github.ott.gateway.service.data.db.OttServiceAccount;

import java.util.List;

public interface IOttAccountService {

    void saveOttAccounts(List<OttServiceAccount> ottServiceAccounts);

    List<OttServiceAccount> getOttAccountsOfUser(String gatewayUserId);
}
