package com.meeting.site.meetingSite.kafka;

import com.meeting.site.meetingSite.model.NewsMessage;
import com.meeting.site.meetingSite.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.meeting.site.meetingSite.config.KafkaReceiverConfig.GROUP_ID_CONFIG;


@Service
@ConditionalOnProperty(value = "example.kafka.consumer-enabled", havingValue = "true")
public class Consumer {
    @Autowired
    private NewsRepository newsRepository;
    @KafkaListener(topics = {"INPUT_DATA"},groupId = GROUP_ID_CONFIG)
    public void consume(final @Payload String message
    ) {
        NewsMessage newsMessage = NewsMessage
                .builder()
                .content(message)
                .timestamp(LocalDateTime.now())
                .build();
        newsRepository.save(newsMessage);
        System.out.println("message - > " + message);
    }
}

