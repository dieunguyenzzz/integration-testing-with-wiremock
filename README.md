# RESTful Integration Testing with WireMock

This project provides examples and guidelines for performing RESTful Integration Testing using WireMock in a Java environment.

## Overview

Integration testing is essential for ensuring that different components of a system work correctly together. In a microservices architecture, where services communicate with each other over RESTful APIs, integration testing becomes crucial to validate the interactions between these services.

WireMock is a powerful tool for stubbing and mocking HTTP-based APIs. It allows you to simulate external service dependencies and define their behavior, making it ideal for integration testing scenarios.

This project demonstrates how to write integration tests for RESTful services using WireMock to mock external dependencies such as downstream services or third-party APIs.

## Features

- Stubbing HTTP endpoints with WireMock to simulate external service behavior.
- Writing integration tests for RESTful services using JUnit and WireMock.
- Handling different HTTP methods (GET, POST, PUT, DELETE) and response codes (200, 404, 500).
- Testing complex response bodies and scenarios.

## Dependencies

- Java 8 or higher
- JUnit Jupiter
- WireMock

## Getting Started

To get started with this project, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/dieunguyenzzz/integration-testing-with-wiremock.git
    ```

2. Navigate to the project directory:

    ```bash
    cd integration-testing-with-wiremock
    ```

3. Open the project in your IDE.

4. Explore the examples provided in the `src/test/java` directory.

5. Run the tests using Maven or your IDE's test runner.

6. Experiment with writing your own integration tests using WireMock.

## Example Scenarios

The examples provided in this project cover various scenarios, including:

- Stubbing different HTTP methods (GET, POST, PUT, DELETE) with WireMock.
- Handling different response codes (200, 404, 500) and complex response bodies.
- Testing scenarios such as error handling, timeouts, and retries.

## Contributing

Contributions are welcome! If you find any issues, have suggestions for improvement, or want to add more examples, please open an issue or create a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
