package com.meeting.site.meetingSite.repository;

import com.meeting.site.meetingSite.model.NewsMessage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<NewsMessage, Long> {
}
