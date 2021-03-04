package com.meeting.site.meetingSite.repository;


import com.meeting.site.meetingSite.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
