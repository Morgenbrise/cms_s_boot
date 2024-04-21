package com.server.cms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(path = "swagger-ui")
    public String api() { return "redirect:/swagger-ui/index.html"; }

}
