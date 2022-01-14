package com.github.ott.gateway.service.service.impl;

import com.github.ott.gateway.service.EnDecryptionException;
import com.github.ott.gateway.service.service.EnDecryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Service
@Slf4j
public class AESEnDecryptionService implements EnDecryptionService {

    private byte[] key = "e7e27e49b3bd784ea4918297a78bb3f7".getBytes();

    private static final String ALGORITHM ="AES";

    @Override
    public String encrypt(String data) {
        try {
           Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return new BASE64Encoder().encode(encryptedBytes);
        } catch (Exception exception) {
            log.error("Exception occurred while encrypting the data", exception);
            throw new EnDecryptionException(exception.getMessage(), exception);
        }
    }

    @Override
    public String decrypt(String encryptedData) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedValue = cipher.doFinal(new BASE64Decoder().decodeBuffer(encryptedData));
            return new String(decryptedValue);
        } catch (Exception exception) {
            log.error("Exception occurred while decrypting the data", exception);
            throw new EnDecryptionException(exception.getMessage(), exception);
        }
    }

    private Key generateKey() {
        return new SecretKeySpec(key, ALGORITHM);
    }
}
