package com.server.cms.controller;

import com.server.cms.model.request.QLogin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping(path = "/auth/login")
    public void login(@RequestBody QLogin.Login param) {

    }

}
