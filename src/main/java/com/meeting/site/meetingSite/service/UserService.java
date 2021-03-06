package com.meeting.site.meetingSite.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meeting.site.meetingSite.model.User;
import com.meeting.site.meetingSite.model.UserFactory;
import com.meeting.site.meetingSite.repository.UserRepository;
import com.meeting.site.meetingSite.security.jwt.JwtService;
import com.meeting.site.meetingSite.support.DateGenerator;
import com.meeting.site.meetingSite.support.StringSupport;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    StringSupport stringSupport;
    @Autowired
    UserFactory userFactory;
    @Autowired
    JwtService jwtService;
    @Autowired
    DateGenerator dateGenerator;
    @Autowired
    ObjectMapper mapper;


    public void create(String username, String password, String role) {
        User byUsername = userRepository.findByUsername(username);
        if(byUsername != null) return;

        String salt = stringSupport.generate();
        User u = userFactory.create(username, DigestUtils.sha256Hex(password), salt, role);
        userRepository.save(u);
    }

    public User isLoginValid(String username, String pass) {
        if (!StringUtils.hasText(username) || !StringUtils.hasText(pass)) {
            return null;
        }
        String password = new String(Base64.decodeBase64(pass.getBytes()));
        User u = userRepository.findByUsername(username);
        if (u == null) {
            return null;
        }
        if (!u.getPassword().equals(DigestUtils.sha256Hex(password))) {

            return null;
        }
        return u;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUserToken(String username, String secret) {
        String token = jwtService.createToken(username, secret, dateGenerator.getExpirationDate());
        User u = userRepository.findByUsername(username);
        u.setToken(token);
        return userRepository.save(u);
    }

    public User validateUser(String token, String secret) {
        String username = jwtService.getUsername(token, secret);
        if (username != null) {
            User user = userRepository.findByUsername(username);
            if (user != null && token.equals(user.getToken()) && jwtService.isValid(token, secret)) {
                return user;
            }
        }
        return null;
    }

    @SneakyThrows
    public String getAll() {
        return mapper.writeValueAsString(userRepository.findAll());
    }
}
