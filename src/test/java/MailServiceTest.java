import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.example.MailService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MailServiceTest {
    private static WireMockServer wireMockServer;
    private static MailService mailService;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8080));
        wireMockServer.start();
        WireMock.configureFor("localhost", 8080);
        mailService = new MailService();
    }

    @AfterAll
    public static void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void testSendEmail() {
        // Setup WireMock stub for mail sending service
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/sendmail"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)));

        // Call the mail service
        boolean result = mailService.sendEmail("example@example.com", "Test Subject", "Test Body");

        // Assert that email is sent successfully
        assertTrue(result);
    }
}
