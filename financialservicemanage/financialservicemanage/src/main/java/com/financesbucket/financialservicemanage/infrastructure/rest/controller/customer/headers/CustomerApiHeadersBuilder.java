package com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.headers;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerApiHeadersBuilder {

    public static HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Bearer your_access_token");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", "application/json");
        headers.add("User-Agent", "QuickFinances/1.0");
        headers.add("Cache-Control", "no-cache");

        return headers;
    }

}
