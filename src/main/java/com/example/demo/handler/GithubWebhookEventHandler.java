package com.example.demo.handler;

import com.example.demo.client.GithubClient;
import com.example.demo.service.EventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

// Annotation to define this class as RESTful web service
@RestController
public class GithubWebhookEventHandler {
    // Logger instance to log events in the class
    private static final Logger log = LoggerFactory.getLogger(GithubWebhookEventHandler.class);
    // GithubClient instance to get commits from Github
    private GithubClient client;
    // EventProcessor instance to process the received events
    private EventProcessor githubEventProcessor;

    // Autowiring the client and eventProcessor instances
    @Autowired
    public GithubWebhookEventHandler(GithubClient client, EventProcessor githubEventProcessor) {
        this.client = client;
        this.githubEventProcessor = githubEventProcessor;
    }

    // Mapping the webhook endpoint to handle POST requests
    @PostMapping("/webhook")
    public void handleWebhook(@RequestHeader(value="X-GitHub-Event") String eventType,@RequestBody String payload) {
        // Reading the X-GitHub-Event header from the request
        log.info("receive eventype {}",eventType);
        // Switch statement to process different types of events
        switch (eventType) {
            case "push":
                githubEventProcessor.processPush(payload);
                break;
            case "pull":
                githubEventProcessor.processPull(payload);
                break;
            case "merge":
                githubEventProcessor.processMerge(payload);
                break;
            default:
                // Logging a warning for unhandled event types
                log.warn("Received unhandled event type: {}", eventType);
                break;
        }
    }

    // Mapping the listCommit endpoint to handle GET requests
    @GetMapping("/list/commit")
    public String listCommit(String repositoryName) {
        // Getting the commits from Github using the GithubClient instance
        String commits = client.getCommits(repositoryName);
        // Logging the received commits
        log.info("Received webhook event: {}", commits);
        return  commits;
    }
}
