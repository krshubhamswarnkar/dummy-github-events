package com.example.demo.client;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class GithubClient {
    private static final Logger log = LoggerFactory.getLogger(GithubClient.class);
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public String getCommits(String repositoryName) {
        String url = String.format("https://api.github.com/repos/%s/commits", repositoryName);
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (Exception e) {
            log.error("Error retrieving commits from Github API", e);
            throw new RuntimeException("Exception occured while fetching the list of commits %s",e);
        }

    }

}
