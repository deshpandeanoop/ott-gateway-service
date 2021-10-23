package com.github.ott.gateway.service.controller;

import com.github.ott.gateway.service.data.request.AddOttServiceAccRequest;
import com.github.ott.gateway.service.data.response.GetOttServiceAccResponse;
import com.github.ott.gateway.service.repository.OttServiceAccRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ottaccounts")
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
    public ResponseEntity<GetOttServiceAccResponse> getOttAccounts(String gatewayUserId) {
        GetOttServiceAccResponse getOttServiceAccResponse = new GetOttServiceAccResponse();
        getOttServiceAccResponse.setOttServiceAccounts(ottServiceAccRepository.getByGateWayServiceUserId(gatewayUserId));
        return ResponseEntity.ok(getOttServiceAccResponse);
    }
}
