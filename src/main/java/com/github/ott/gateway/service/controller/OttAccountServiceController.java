package com.github.ott.gateway.service.controller;

import com.github.ott.gateway.service.data.db.OttServiceAccount;
import com.github.ott.gateway.service.data.request.AddOttServiceAccRequest;
import com.github.ott.gateway.service.data.response.GetOttServiceAccResponse;
import com.github.ott.gateway.service.enums.OttPlatformType;
import com.github.ott.gateway.service.repository.OttServiceAccRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1/ottaccounts")
@RestController
@Slf4j
public class OttAccountServiceController {

    @Autowired
    private OttServiceAccRepository ottServiceAccRepository;

    @PostMapping
    public ResponseEntity<String> addOttAccounts(@RequestBody AddOttServiceAccRequest ottServiceAccRequest) {
        ottServiceAccRepository.saveAll(ottServiceAccRequest.getOttServiceAccountsDetails());
        return ResponseEntity.ok("Success!");
    }

    @GetMapping
    public ResponseEntity<GetOttServiceAccResponse> getOttAccounts(@RequestParam("gatewayUserId") String gatewayUserId,
                                                                   @RequestParam("fetchOnlyNames") boolean fetchOnlyNames,
                                                                   @RequestParam("platformType") String platformType) {
        GetOttServiceAccResponse getOttServiceAccResponse = new GetOttServiceAccResponse();
        List<OttServiceAccount> serviceAccounts = ottServiceAccRepository.getByGateWayServiceUserId(gatewayUserId);
        /**
         * Ideally the predicate push down should happen, since the product is in prototyping phase
         * adding in the filtering logic in the controller layer for now.
         */

        if(StringUtils.hasText(platformType) && OttPlatformType.isValid(platformType)) {
            serviceAccounts = Collections.singletonList(serviceAccounts
                    .stream()
                    .filter(ottServiceAccount -> ottServiceAccount.getPlatformType().name().equals(platformType))
                    .findFirst().get());
        }

        if(fetchOnlyNames) {
            log.info("FetchOnlyNames flag is passed as true, extracting and returning only OTT platform names");
            serviceAccounts = serviceAccounts
                    .stream()
                    .map(serviceAcc -> new OttServiceAccount("", "", serviceAcc.getPlatformType(), serviceAcc.getGateWayServiceUserId()))
                    .collect(Collectors.toList());
        }
        getOttServiceAccResponse.setOttServiceAccounts(serviceAccounts);

        return ResponseEntity.ok(getOttServiceAccResponse);
    }
}
