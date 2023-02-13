package com.example.demo.service.impl;

import com.example.demo.service.EventProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GithubEventProcessor implements EventProcessor {

    @Override
    public void processMerge(String payload) {
        log.info("received the merge event form the github {}",payload);
    }

    @Override
    public void processPush(String payload) {
        log.info("received the push event form the github {}",payload);
    }

    @Override
    public void processPull(String payload) {
        log.info("received the pull event form the github {}",payload);
    }
}
