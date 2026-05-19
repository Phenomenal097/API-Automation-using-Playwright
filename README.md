# API Automation using Playwright

API Automation using Playwright is a Java-based API testing framework built with **Playwright for Java** and **TestNG**. It validates REST API flows such as creating users, fetching users, and verifying response data using request and response models.

## Tech Stack

- Java
- Playwright for Java
- TestNG
- Maven
- Jackson Databind
- Lombok
- GoRest API

## Features

- API automation using Playwright request context
- GET and POST API test coverage
- Request and response model classes
- Test data generation for user payloads
- Status code and status text validation
- Response body deserialization using Jackson
- Maven Surefire test execution

## Project Structure

```text
API-Automation-using-Playwright/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── framework/
│   │               ├── requestmodel/
│   │               ├── responsemodel/
│   │               └── utility/
│   │                   ├── enums/
│   │                   └── testdata/
│   └── test/
│       └── java/
│           └── com/
│               └── framework/
│                   ├── basetest/
│                   └── tests/
├── pom.xml
└── README.md
```

## Prerequisites

- Java 24
- Maven
- Git
- GoRest API access token

## Setup

Clone the repository:

```
git clone https://github.com/Phenomenal097/API-Automation-using-Playwright.git
cd API-Automation-using-Playwright
```

Install project dependencies:

```
mvn clean install
```

## Run Tests

Run all API tests:

```
mvn test
```

Run a specific test class:

```
mvn test -Dtest=CreateUserTest
```

```
mvn test -Dtest=GetUsersTest
```

```
mvn test -Dtest=GetUserTest
```

## Test Scenarios

- Create a user using POST API
- Get all users using GET API
- Create a user and validate the user exists in the GET users response
- Create a user and validate the created user details using GET user by ID

## API Used

Base API:

```
https://gorest.co.in/public/v2/users
```

## Notes

- A valid GoRest bearer token is required for POST API execution.
- Keep the API token secure and avoid committing private tokens directly in the source code.

## Author

[Phenomenal097](https://github.com/Phenomenal097)
