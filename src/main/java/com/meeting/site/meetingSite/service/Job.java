package com.meeting.site.meetingSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class Job {
    RestTemplate restTemplate = new RestTemplate();
    private int counter = 0;
    @Autowired
    private LRUCache cache;

    @Async("threadPoolTaskExecutor")
    public void acyncJob(UUID uuid) {
        try {
            String readFile = readFile("src/main/resources/msg.json", StandardCharsets.US_ASCII);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJRNVI4aVFYVko0dGF3ZHhpYzQxRVlHV2pNREdLYW1Ib0F0eUZiMnlfSHBRIn0.eyJqdGkiOiJiYzMyODYzMi1hZmQ3LTRkNTUtODViMS04MjM3N2YyYTg4YTEiLCJleHAiOjE2MTQ5NzcyMDcsIm5iZiI6MCwiaWF0IjoxNjE0OTQ4NDA3LCJpc3MiOiJodHRwczovL3Rlc3Qub2Jvei5hcHAvYXV0aC9yZWFsbXMvb2JvejIiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNGQ3ODZkZGYtZjRlYS00ODRhLWIxMWEtNWQ2MzdmNTgyNTk0IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiaW50ZXJuYWxfY3Jvc3Nfc2VydmljZV9pbnRlcmFjdGlvbiIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6IjQwM2I0NDUyLTM5ZTEtNGU3MC1iZTMzLTJiNWYzZjU2MDU0YSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudElkIjoiaW50ZXJuYWxfY3Jvc3Nfc2VydmljZV9pbnRlcmFjdGlvbiIsImNsaWVudEhvc3QiOiIxMC4yMzMuNjUuMCIsInByZWZlcnJlZF91c2VybmFtZSI6InNlcnZpY2UtYWNjb3VudC1pbnRlcm5hbF9jcm9zc19zZXJ2aWNlX2ludGVyYWN0aW9uIiwiY2xpZW50QWRkcmVzcyI6IjEwLjIzMy42NS4wIiwiZW1haWwiOiJzZXJ2aWNlLWFjY291bnQtaW50ZXJuYWxfY3Jvc3Nfc2VydmljZV9pbnRlcmFjdGlvbkBwbGFjZWhvbGRlci5vcmcifQ.Xk9BCK4iwgHzt8hvDvmoY9vEGr7IsWVJoIeCzy1zhDKxpSDdYQziRD9FMA9P1-oK2fUPHtCiDzynNScmP9JQPsNuKLQWFkjvrwWp91UP7VapYgxNb5xtwcrevQUtMmQPSvJ8l_UK2zvr7zWcmxxDc9FCVSSYhCm20EXhX9mXz23KCXGZt8NljmhopIyTEgM4jfkIGO6HTxJacvgrPWF3oQnQSv2-5ejl7DpqAngpOzOwQxBk4o0UaA6P1HTuwHxxEuChfj4CB99XHkZp5tfvaEFv9wgKN5GNADUgGqp4JtPodA82LSRe6cdVCZQ0zX-jw6Iu1SReXOfW5A7gbPvZaw");
            HttpEntity<String> en = new HttpEntity<>(readFile,headers);

            ResponseEntity<String> forEntity = restTemplate.postForEntity("http://localhost:8080/v1/internal/send_order", en,String.class);
            System.out.println("response -> " + forEntity.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
