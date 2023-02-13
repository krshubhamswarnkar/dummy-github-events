package com.example.demo.service;

public interface EventProcessor {
    void processMerge(String payload);
    void processPush(String payload);
    void processPull(String payload);
}
