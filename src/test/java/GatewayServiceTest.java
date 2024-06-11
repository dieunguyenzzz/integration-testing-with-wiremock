import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.example.GatewayService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GatewayServiceTest{
    private static WireMockServer weatherServiceServer;
    private static WireMockServer stockServiceServer;
    private static WireMockServer newsServiceServer;
    private static GatewayService gatewayService;

    @BeforeAll
    public static void setup() {
        weatherServiceServer = new WireMockServer(wireMockConfig().port(8081));
        weatherServiceServer.start();
        stockServiceServer = new WireMockServer(wireMockConfig().port(8082));
        stockServiceServer.start();
        newsServiceServer = new WireMockServer(wireMockConfig().port(8083));
        newsServiceServer.start();

        WireMock.configureFor("localhost", 8081);
        setupWeatherServiceStub();

        WireMock.configureFor("localhost", 8082);
        setupStockServiceStub();

        WireMock.configureFor("localhost", 8083);
        setupNewsServiceStub();

        gatewayService = new GatewayService();
    }

    @AfterAll
    public static void teardown() {
        weatherServiceServer.stop();
        stockServiceServer.stop();
        newsServiceServer.stop();
    }

    @Test
    public void testFetchWeatherData() throws IOException {
        String weatherData = gatewayService.fetchWeatherData("http://localhost:8081/weather");
        assertEquals("Weather: Sunny", weatherData);
    }

    @Test
    public void testFetchStockData() throws IOException {
        String stockData = gatewayService.fetchStockData("http://localhost:8082/stock");
        assertEquals("Stock: AAPL $150", stockData);
    }

    @Test
    public void testFetchNewsData() throws IOException {
        String newsData = gatewayService.fetchNewsData("http://localhost:8083/news");
        assertEquals("News: Breaking News", newsData);
    }

    private static void setupWeatherServiceStub() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/weather"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("Weather: Sunny")));
    }

    private static void setupStockServiceStub() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/stock"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("Stock: AAPL $150")));
    }

    private static void setupNewsServiceStub() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/news"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("News: Breaking News")));
    }
}
