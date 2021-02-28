package com.meeting.site.meetingSite.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meeting.site.meetingSite.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private ObjectMapper mapper;

    @SneakyThrows
    public String getAll() {
        return mapper.writeValueAsString(repository.findAll());
    }
}
