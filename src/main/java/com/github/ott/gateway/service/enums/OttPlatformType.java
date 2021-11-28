package com.github.ott.gateway.service.enums;

import java.util.Arrays;

/**
 * Holds type of OTT platform supported by the gateway platform
 */
public enum OttPlatformType {
    AMAZON_PRIME, NETFLIX;

    public static boolean isValid(String val) {
        return Arrays.stream(values())
                .anyMatch(el -> el.name().equals(val));
    }
}
