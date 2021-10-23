package com.github.ott.gateway.service.data.request;

import com.github.ott.gateway.service.data.db.OttServiceAccount;
import lombok.Data;

import java.util.List;

@Data
public class AddOttServiceAccRequest {

    private List<OttServiceAccount> ottServiceAccountsDetails;
}