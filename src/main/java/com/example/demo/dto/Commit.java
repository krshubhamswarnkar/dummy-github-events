package com.example.demo.dto;


public  class Commit {
    String sha;
    CommitDetails commit;

    private static class CommitDetails {
        String message;
    }
}