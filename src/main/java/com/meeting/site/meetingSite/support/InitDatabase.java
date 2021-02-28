package com.meeting.site.meetingSite.support;

import com.meeting.site.meetingSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InitDatabase {


    @Autowired
    public InitDatabase(UserService userService) {
        userService.create("admin", "admin", "USER");
        userService.create("tomcat", "tomcat", "USER");
    }


}
