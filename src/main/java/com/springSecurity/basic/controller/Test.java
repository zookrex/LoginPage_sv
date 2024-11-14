package com.springSecurity.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api1")
public class Test {

    @GetMapping
    public String test() {
        return "test";
    }
}
