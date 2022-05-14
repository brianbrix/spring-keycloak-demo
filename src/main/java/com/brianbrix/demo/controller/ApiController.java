package com.brianbrix.demo.controller;

import com.brianbrix.demo.models.request.TokenRequest;
import com.brianbrix.demo.models.response.TokenResponse;
import com.brianbrix.demo.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    private final TokenService tokenService;
    @GetMapping("")
    String test()
    {
        return "Test successful";
    }
    @PostMapping(value = "token", consumes = MediaType.APPLICATION_JSON_VALUE)
    TokenResponse getToken(@RequestBody TokenRequest tokenRequest)
    {
        return tokenService.getToken(tokenRequest);
    }

}
