package org.example;
import java.util.Random;
public class ComplexService {
    private ExternalDependency1 externalDependency1;
    private ExternalDependency2 externalDependency2;

    public ComplexService(ExternalDependency1 externalDependency1, ExternalDependency2 externalDependency2) {
        this.externalDependency1 = externalDependency1;
        this.externalDependency2 = externalDependency2;
    }

    public String performComplexOperation() {
        // Perform complex logic involving external dependencies
        String result = externalDependency1.getData() + " " + externalDependency2.getData();

        // Simulate long processing time
        try {
            Thread.sleep(5000); // Sleep for 5 seconds to simulate a long operation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return result.toUpperCase();
    }
}
