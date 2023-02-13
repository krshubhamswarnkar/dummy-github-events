package com.example.demo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class GithubEventProcessorTest {
    private static final String TEST_PAYLOAD = "test payload";

    @Mock
    private Logger logger;

    @InjectMocks
    private GithubEventProcessor githubEventProcessor;

    @Test
    public void testProcessMerge() {
        githubEventProcessor.processMerge(TEST_PAYLOAD);
    }

    @Test
    public void testProcessPush() {
        githubEventProcessor.processPush(TEST_PAYLOAD);
    }

    @Test
    public void testProcessPull() {
        githubEventProcessor.processPull(TEST_PAYLOAD);
    }
}
