package com.meeting.site.meetingSite.controller;

import com.meeting.site.meetingSite.kafka.Consumer;
import com.meeting.site.meetingSite.model.NewsMessage;
import com.meeting.site.meetingSite.repository.NewsRepository;
import com.meeting.site.meetingSite.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Fuseable;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(value = { "http://localhost:3000","http://localhost:8080" },
        allowedHeaders = { "*" },
        maxAge = 900
)
public class NewsController {

    @Autowired
    private KafkaReceiver kafkaReceiver;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsService newsService;

    @CrossOrigin(allowedHeaders = "*")
    @GetMapping(value = "/event/news", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Async("conc-task-executor")
    public Flux<NewsMessage> getNews() {
        Flux<ReceiverRecord<String, String>> kafkaFlux = kafkaReceiver.receive();
        return kafkaFlux.checkpoint("Messages are started being consumed")
                .log()
                .doOnNext(r -> r.receiverOffset().acknowledge())
                .map(it -> {
                    NewsMessage newsMessage = NewsMessage
                            .builder()
                            .content(it.value())
                            .timestamp(LocalDateTime.now())
                            .build();
                    newsRepository.save(newsMessage);
                    return newsMessage;

                })
                .checkpoint("Messages are done consumed");
    }

    @CrossOrigin(allowedHeaders = "*")
    @GetMapping(value = "/event/last/news", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NewsMessage> getLastNews() {
        return newsService.getLastNews();
    }
}
