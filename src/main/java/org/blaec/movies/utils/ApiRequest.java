package org.blaec.movies.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class ApiRequest {
    public static HttpResponse<String> sendRequest(String url) {
        // http://zetcode.com/java/getpostrequest/
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> send = null;
        try {
            send = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(send, "failed to send request");
        return send;
    }
}
