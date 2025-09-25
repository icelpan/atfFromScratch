
# Task Description: Build an Automation Test Framework (ATF)

## Objective

Develop a scalable and modular Automation Test Framework (ATF) in **Java** that supports:

- UI testing using **Playwright**
- API testing using **RestAssured**
- **Behavior-Driven Development (BDD)** using Cucumber
- Scenario context handling
- Centralized logging
- Test reporting

---

## Technologies to Use

- **Language**: Java 17+
- **Web Automation**: Playwright for Java
- **API Testing**: RestAssured
- **BDD**: Cucumber
- **Build Tool**: Maven
- **Test Runner**: JUnit
- **Logging**: SLF4J + Logback (or Log4j2)
- **Reporting**: ExtentReports or Allure
- **Optional Tools**: Faker (test data)

---

## Deliverables

The completed framework should include the following components:

### 1. UI Automation (Playwright)

- Implement a browser driver manager using Playwright.
- Support multiple browsers (Chromium, Firefox, WebKit).
- Handle dynamic content using appropriate wait strategies.
- Take screenshots on failure.
- Optionally, enable trace and video recording.
- Launch browsers in headless and non-headless mode.

### 2. API Automation (RestAssured)

- Create a base API class with default request/response specifications.
- Include support for common HTTP methods: GET, POST, PUT, DELETE.
- Support dynamic header injection (e.g., bearer token) if applicable
- Allow validation of status codes, headers, and response payloads.
- Enable easy data-driven testing.

### 3. BDD Layer (Cucumber)

- Create `.feature` files grouped by feature or module.
- Define step definitions mapped clearly to BDD steps.
- Implement tag-based execution (e.g., `@api`, `@ui`, `@regression`).
- Include lifecycle hooks:
  - `@BeforeAll`: Global setup
  - `@Before`: Test-level setup (browser/API client, logger)
  - `@After`: Screenshot capture and logging
  - `@AfterAll`: Final teardown and report finalization

### 4. Scenario Context

- Implement a `ScenarioContext` class using `Map<String, Object>`.
- Allow steps to share data across the same scenario.


### 5. Logging

- Use SLF4J abstraction with Logback (or Log4j2) as the backend.
- Create a wrapper utility for logging at different levels: DEBUG, INFO, ERROR.
- Configure rolling file logs and console output.
- Mask sensitive data like tokens, passwords, etc.

### 6. Reporting

#### Option A: ExtentReports

- Generate interactive HTML reports with:
  - Feature-wise breakdown
  - Screenshots on failure
  - Test start/end time
  - Tags and execution metadata

#### Option B: Allure Reports

- Integrate with Maven and Cucumber
- Attach:
  - Screenshots
  - API requests/responses
  - Logs
  - Video (optional)

### 7. Configuration Management

- Use a `config.properties` file to manage:
  - Base URLs
  - Timeouts
  - Browser type

### 8. Execution Strategy

- Implement a test runner (JUnit) with tag filtering.

---

## Acceptance Criteria

- [ ] **Number of tests** → Each team member should cover at least 3-5 tests in BDD
- [ ] **Knowledge** → Each team member could explain any class or method in the framework
- [ ] **Tags** → Test runner should be parameterized by Tags, so user can execute tests for each team member or business area separately
- [ ] **Logs** → Each step to be covered with logs in file and console
- [ ] **API** → Tests should use any API library for API testing
- [ ] **Design patterns** → Framework should contain design patterns (Factory, Singleton, Page Object etc.)
- [ ] **Step implementation** → Use common and generic steps as much as possible
- [ ] **Report** → It should be possibility to have cucumber report at the end of run
- [ ] **Properties** → Use property files to store test properties (no hardcoded properties)
---
