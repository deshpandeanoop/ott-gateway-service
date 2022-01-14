package com.github.ott.gateway.service.service;

public interface EnDecryptionService {

    String encrypt(String data);

    String decrypt(String encryptedData);
}