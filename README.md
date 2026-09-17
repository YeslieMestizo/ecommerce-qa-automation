# Ecommerce QA Automation

Web UI test automation project developed with **Java, Selenium WebDriver, JUnit 5, and Maven**.

The project is being built as a practical QA/SDET portfolio project, focusing on test design, maintainable automation, and reliable UI testing.

## Tech Stack

- Java 21
- Selenium WebDriver
- JUnit 5
- Maven
- IntelliJ IDEA

## Current Test Coverage

Automated authentication scenarios include:

- Valid login
- Invalid credentials
- Empty email validation
- Empty password validation
- Empty email and password validation

## Automation Practices

The project currently applies:

- Page Object Model (POM)
- Explicit waits with `WebDriverWait`
- JUnit assertions
- Positive and negative testing
- Boundary Value Analysis
- Equivalence Partitioning
- Environment variables for sensitive test data
- Analysis and debugging of flaky UI test behavior

## Project Structure

```text
src/test/java
├── pages
│   └── LoginPage.java
└── tests
    └── LoginTest.java
