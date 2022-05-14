package com.brianbrix.demo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("")
    String test()
    {
        return "Test successful";
    }

}
