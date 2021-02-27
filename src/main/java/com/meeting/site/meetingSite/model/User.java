package com.meeting.site.meetingSite.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CollectionId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    private Long id;
    @Column(columnDefinition = "name")
    private String name;
    @Column(columnDefinition = "password")
    private String password;

}
