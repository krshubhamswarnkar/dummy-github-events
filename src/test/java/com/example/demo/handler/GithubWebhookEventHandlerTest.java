package com.example.demo.handler;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.client.GithubClient;
import com.example.demo.service.EventProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class GithubWebhookEventHandlerTest {
    private static final Logger log = LoggerFactory.getLogger(GithubWebhookEventHandlerTest.class);

    private MockMvc mockMvc;

    @Mock
    private GithubClient client;

    @Mock
    private EventProcessor githubEventProcessor;

    @InjectMocks
    private GithubWebhookEventHandler handler;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(handler).build();
    }

    @Test
    public void testHandleWebhook_push() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-GitHub-Event", "push");

        mockMvc.perform(post("/webhook")
                        .content("{\"payload\":\"test_payload\"}")
                        .headers(headers))
                .andExpect(status().isOk());
    }

    @Test
    public void testHandleWebhook_pull() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-GitHub-Event", "pull");

        mockMvc.perform(post("/webhook")
                        .content("{\"payload\":\"test_payload\"}")
                        .headers(headers))
                .andExpect(status().isOk());
    }

    @Test
    public void testHandleWebhook_merge() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-GitHub-Event", "merge");

        mockMvc.perform(post("/webhook")
                        .content("{\"payload\":\"test_payload\"}")
                        .headers(headers))
                .andExpect(status().isOk());
    }

    @Test
    public void testHandleWebhook_default() throws Exception {
        HttpHeaders headers = new HttpHeaders();

    }



        @Test
    public void testListCommit() {
            String repositoryName = "test-repository";
            String commits = "test-commits";
            when(client.getCommits(repositoryName)).thenReturn(commits);

            String result = handler.listCommit(repositoryName);
            verify(client, times(1)).getCommits(repositoryName);
            assertEquals(commits, result);
        }

}