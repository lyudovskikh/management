# User Management Service - Getting Started

### Java Version: 11

### Build and Run
1. ``gradle build``
2. ``gradle bootRun``

### Useful links:
- http://localhost:8080/h2-console - H2 database web console
- http://localhost:8080/v3/api-docs - Swagger OpenAPI specs
- http://localhost:8080/swagger-ui.html - Swagger UI

### Swagger (OpenAPI) documentation:
 Build project and open ``/management/build/openapi.json``
 
 
# Task

 We want you to build a new user management service. The service must offer CRUD operations
 for the users. The users must be unique, they should have basic information and their first, last
 name must not exceed 100 characters.
 
 In order to achieve this, we want you to build a new service that exposes an API with the
 following specifications:
 
 1) Creates new user    
    a. The API should accept only valid data
 2) Returns all the users
 3) Returns a user by ID
 4) Deletes a user
 5) Updates a users data
 
 ## Acceptance Criteria
 
 ### Scenario: Create valid user
 Given I am a user
 
 When I submit valid data for a new user
 
 Then the user is created
 
 ### Scenario: Retrieve User
 
 Given I am a user
 
 When I request to retrieve a user by Id
 
 Then I can see the user’s basic information
 
 ### Scenario: Retrieve all users
 
 Given I am a user
 
 When I request to retrieve all users
 
 Then I can see all the users
 
 And when I filter by first name
 
 Then I retrieve only the users having a similar first name
 
 
 ### Scenario: Delete User
 
 Given I am a user
 
 When I request to delete a user by Id
 
 Then the user is deleted
 
 ## What is expected
 
 You have freedom in how you implement the solution, but a few things should appear in the final
 submission:
 - The solution must run on a single machine.
 - The solution must be provided in Java 11+
 - The solution must contain Unit tests
 - The solution must use Gradle
 - A swagger document must be included to the final deliverable
 
 ## What isn&#39;t expected
 - You do not need to implement any authentication
 
 ## Submitting your test
 Please submit a zip file with your full project