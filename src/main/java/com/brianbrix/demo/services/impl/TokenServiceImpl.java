package com.brianbrix.demo.services.impl;

import com.brianbrix.demo.models.request.TokenRequest;
import com.brianbrix.demo.models.response.TokenResponse;
import com.brianbrix.demo.services.TokenService;
import com.brianbrix.demo.utils.GenericWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private WebClient webClient;

    @Override
    public TokenResponse getToken(TokenRequest tokenRequest) {
        GenericWebClient<TokenResponse, TokenRequest> genericWebClient= new GenericWebClient<>();
        String tokenBase = "https://localhost:8443";
        String tokenPath = "/realms/test/protocol/openid-connect/token";
        return genericWebClient.makeRequest(webClient, tokenBase, tokenPath,tokenRequest, TokenResponse.class);
    }
}
