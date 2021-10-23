package com.github.ott.gateway.service.data.response;

import com.github.ott.gateway.service.data.db.OttServiceAccount;
import lombok.Data;

import java.util.List;

@Data
public class GetOttServiceAccResponse {

    private List<OttServiceAccount> ottServiceAccounts;
}