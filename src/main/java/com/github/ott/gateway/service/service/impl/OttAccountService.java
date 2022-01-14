package com.github.ott.gateway.service.service.impl;

import com.github.ott.gateway.service.data.db.OttServiceAccount;
import com.github.ott.gateway.service.repository.OttServiceAccRepository;
import com.github.ott.gateway.service.service.EnDecryptionService;
import com.github.ott.gateway.service.service.IOttAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OttAccountService implements IOttAccountService {

    private final OttServiceAccRepository ottServiceAccRepository;
    private final EnDecryptionService enDecryptionService;

    @Override
    public void saveOttAccounts(List<OttServiceAccount> ottServiceAccounts) {
        List<OttServiceAccount> encryptedOttServiceAccounts =
                ottServiceAccounts
                .stream()
                .map(this::getEncryptedOttServiceAccount)
                .collect(Collectors.toList());

        ottServiceAccRepository.saveAll(encryptedOttServiceAccounts);
    }

    @Override
    public List<OttServiceAccount> getOttAccountsOfUser(String gatewayUserId) {
        return ottServiceAccRepository.getByGateWayServiceUserId(gatewayUserId)
                .stream()
                .map(this::getDecryptedServiceAccount)
                .collect(Collectors.toList());
    }

    private OttServiceAccount getEncryptedOttServiceAccount(OttServiceAccount ottServiceAccount) {
        return new OttServiceAccount(
                enDecryptionService.encrypt(ottServiceAccount.getUsername()),
                enDecryptionService.encrypt(ottServiceAccount.getPassword()),
                ottServiceAccount.getPlatformType(),
                ottServiceAccount.getGateWayServiceUserId());
    }

    private OttServiceAccount getDecryptedServiceAccount(OttServiceAccount ottServiceAccount) {
        return new OttServiceAccount(
          enDecryptionService.decrypt(ottServiceAccount.getUsername()),
          enDecryptionService.decrypt(ottServiceAccount.getPassword()),
          ottServiceAccount.getPlatformType(),
          ottServiceAccount.getGateWayServiceUserId());
    }
}
