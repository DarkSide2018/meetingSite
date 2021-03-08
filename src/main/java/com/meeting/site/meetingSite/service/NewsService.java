package com.meeting.site.meetingSite.service;

import com.meeting.site.meetingSite.model.NewsMessage;
import com.meeting.site.meetingSite.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    @Autowired
    private NewsRepository repository;


    public List<NewsMessage> getLastNews() {
        List<NewsMessage> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list.stream().limit(10).collect(Collectors.toList());
    }
}
