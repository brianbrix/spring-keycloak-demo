package com.brianbrix.demo.services.impl;

import com.brianbrix.demo.models.request.TokenRequest;
import com.brianbrix.demo.models.response.TokenResponse;
import com.brianbrix.demo.services.TokenService;
import com.brianbrix.demo.utils.GenericWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private WebClient webClient;

    @Override
    public TokenResponse getToken(TokenRequest tokenRequest) {
        GenericWebClient<TokenResponse, TokenRequest> genericWebClient= new GenericWebClient<>();
        String tokenBase = "https://127.0.0.1:8443";
        String tokenPath = "/realms/test/protocol/openid-connect/token";
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.set("client_id", tokenRequest.getClientId());
        valueMap.set("username", tokenRequest.getUsername());
        valueMap.set("password", tokenRequest.getPassword());
        valueMap.set("grant_type", tokenRequest.getGrantType());
        return genericWebClient.makeRequest(webClient, tokenBase, tokenPath,valueMap, TokenResponse.class);
    }
}
