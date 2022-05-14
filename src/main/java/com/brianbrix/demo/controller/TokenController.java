package com.brianbrix.demo.controller;

import com.brianbrix.demo.models.request.TokenRequest;
import com.brianbrix.demo.models.response.TokenResponse;
import com.brianbrix.demo.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;
    @PostMapping(value = "token", consumes = MediaType.APPLICATION_JSON_VALUE)
    TokenResponse getToken(@RequestBody TokenRequest tokenRequest)
    {
        return tokenService.getToken(tokenRequest);
    }

}
