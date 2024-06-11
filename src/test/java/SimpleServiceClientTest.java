import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.example.SimpleServiceClient;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleServiceClientTest {
    private static WireMockServer wireMockServer;
    private static SimpleServiceClient client;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8080));
        wireMockServer.start();
        WireMock.configureFor("localhost", 8080);
        client = new SimpleServiceClient();
    }

    @AfterAll
    public static void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void testGetStatus() throws IOException {
        // Setup WireMock stub
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/test"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("Success")));

        // Call the client
        int responseCode = client.getStatus("http://localhost:8080/test");
        String responseBody = client.getResponseBody();

        // Assert the response
        assertEquals(200, responseCode);
        assertEquals("Success", responseBody);
    }

    @Test
    public void testPostMethod() throws IOException {
        // Setup WireMock stub for POST request
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/post"))
                .withRequestBody(WireMock.equalToJson("{\"name\":\"John\"}"))
                .willReturn(WireMock.aResponse()
                        .withStatus(201)
                        .withBody("{\"id\":123,\"name\":\"John\"}")));

        // Call the client
        int responseCode = client.postData("http://localhost:8080/post", "{\"name\":\"John\"}");
        String responseBody = client.getResponseBody();

        // Assert the response
        assertEquals(201, responseCode);
        assertEquals("{\"id\":123,\"name\":\"John\"}", responseBody);
    }

    @Test
    public void testPutMethod() throws IOException {
        // Setup WireMock stub for PUT request
        WireMock.stubFor(WireMock.put(WireMock.urlEqualTo("/put/123"))
                .withRequestBody(WireMock.equalToJson("{\"name\":\"Jane\"}"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("{\"id\":123,\"name\":\"Jane\"}")));

        // Call the client
        int responseCode = client.putData("http://localhost:8080/put/123", "{\"name\":\"Jane\"}");
        String responseBody = client.getResponseBody();

        // Assert the response
        assertEquals(200, responseCode);
        assertEquals("{\"id\":123,\"name\":\"Jane\"}", responseBody);
    }

    @Test
    public void testDeleteMethod() throws IOException {
        // Setup WireMock stub for DELETE request
        WireMock.stubFor(WireMock.delete(WireMock.urlEqualTo("/delete/123"))
                .willReturn(WireMock.aResponse()
                        .withStatus(204)));

        // Call the client
        int responseCode = client.deleteData("http://localhost:8080/delete/123");

        // Assert the response
        assertEquals(204, responseCode);
    }

    @Test
    public void testNotFoundResponse() throws IOException {
        // Setup WireMock stub for 404 response
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/notfound"))
                .willReturn(WireMock.aResponse()
                        .withStatus(404)
                        .withBody("Not Found")));

        // Call the client
        int responseCode = client.getStatus("http://localhost:8080/notfound");
        String responseBody = client.getResponseBody();

        // Assert the response
        assertEquals(404, responseCode);
        assertEquals("Not Found", responseBody);
    }

    @Test
    public void testInternalServerErrorResponse() throws IOException {
        // Setup WireMock stub for 500 response
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/error"))
                .willReturn(WireMock.aResponse()
                        .withStatus(500)
                        .withBody("Internal Server Error")));

        // Call the client
        int responseCode = client.getStatus("http://localhost:8080/error");
        String responseBody = client.getResponseBody();

        // Assert the response
        assertEquals(500, responseCode);
        assertEquals("Internal Server Error", responseBody);
    }

    @Test
    public void testComplexJsonResponse() throws IOException {
        // Setup WireMock stub for complex JSON response
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/complex"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"user\":{\"id\":1,\"name\":\"John Doe\",\"roles\":[\"admin\",\"user\"]}}")));

        // Call the client
        int responseCode = client.getStatus("http://localhost:8080/complex");
        String responseBody = client.getResponseBody();

        // Assert the response
        assertEquals(200, responseCode);
        assertEquals("{\"user\":{\"id\":1,\"name\":\"John Doe\",\"roles\":[\"admin\",\"user\"]}}", responseBody);
    }
}
