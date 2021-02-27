package com.meeting.site.meetingSite.controller;

import com.meeting.site.meetingSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping(path = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAll() {
        return service.getAll();
    }
}
