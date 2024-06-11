import org.example.ComplexService;
import org.example.ExternalDependency1;
import org.example.ExternalDependency2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexServiceTest {
    @Test
    public void testPerformComplexOperation() {
        // Mock external dependencies
        ExternalDependency1 externalDependency1 = Mockito.mock(ExternalDependency1.class);
        ExternalDependency2 externalDependency2 = Mockito.mock(ExternalDependency2.class);

        // Stubbing behavior of external dependencies
        Mockito.when(externalDependency1.getData()).thenReturn("Mocked Data from External Dependency 1");
        Mockito.when(externalDependency2.getData()).thenReturn("Mocked Data from External Dependency 2");

        // Create instance of ComplexService with mocked dependencies
        ComplexService complexService = new ComplexService(externalDependency1, externalDependency2);

        // Call the method to test
        String result = complexService.performComplexOperation();

        // Assert the result
        assertEquals("MOCKED DATA FROM EXTERNAL DEPENDENCY 1 MOCKED DATA FROM EXTERNAL DEPENDENCY 2", result);
    }
}
