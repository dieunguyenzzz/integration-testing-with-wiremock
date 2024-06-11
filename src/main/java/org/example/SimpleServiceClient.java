package org.example;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
public class SimpleServiceClient {
    private String responseBody;
    public int getStatus(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con.getResponseCode();
    }
    public int postData(String endpoint, String jsonInputString) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return readResponse(con);
    }

    public int putData(String endpoint, String jsonInputString) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return readResponse(con);
    }

    public int deleteData(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        return readResponse(con);
    }

    private int readResponse(HttpURLConnection con) throws IOException {
        int status = con.getResponseCode();
        InputStream responseStream = (status < HttpURLConnection.HTTP_BAD_REQUEST)
                ? con.getInputStream()
                : con.getErrorStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(responseStream, "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            responseBody = response.toString();
        }
        return status;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
