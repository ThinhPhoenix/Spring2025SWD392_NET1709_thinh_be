package com.fpt.swd392.cvsts.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class TestController {

    @GetMapping
    public Map<String, String> getPong() {
        return Map.of("message", "pong");
    }

}
