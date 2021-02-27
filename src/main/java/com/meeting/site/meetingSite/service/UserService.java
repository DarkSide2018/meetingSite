package com.meeting.site.meetingSite.service;

import com.meeting.site.meetingSite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public String getAll(){
        return repository.findAll().toString();
    }
}
