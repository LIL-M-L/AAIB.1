# AAIB Technical Assessment - API Automation

## Project Overview

This project implements automated API tests for the user management workflow using the public API available at [https://reqres.in](https://reqres.in). The tests demonstrate data extraction from responses and using that data in subsequent requests, as required by the technical assessment.

## Features Implemented

### ✅ Complete User Management Workflow
1. **Create User**: POST /api/users (captures ID from response)
2. **Update User**: PUT /api/users/{id} (updates job using captured ID)
3. **Get User**: GET /api/users/{id} (verifies job was updated)
4. **Delete User**: DELETE /api/users/{id}
5. **Verify Deletion**: GET /api/users/{id} (verifies user is not found - 404)

### ✅ Key Testing Features
- **Data Extraction**: Captures user ID from POST response and uses it in subsequent requests
- **Comprehensive Assertions**: Validates status codes, response bodies, and data integrity
- **Test Prioritization**: Uses TestNG priority annotations for proper test execution order
- **Allure Reporting**: Integrated test reporting with detailed test execution results
- **Proper Error Handling**: Validates expected error responses (404 for deleted users)

## Technology Stack

- **Language**: Java 21
- **Build Tool**: Maven
- **Testing Framework**: TestNG
- **API Testing**: Rest Assured 5.5.4
- **Reporting**: Allure Framework 2.24.0
- **JSON Handling**: Jackson & JSON Simple
- **API Endpoint**: https://reqres.in

## Project Structure

```
AAIB-PROJECT/
├── pom.xml                          # Maven configuration with dependencies
├── README.md                        # Project documentation
└── src/
    ├── main/java/org/example/
    │   └── Main.java               # Main application class
    └── test/java/
        └── AAIB.java               # API test automation suite
```

## Test Cases

### Individual Test Methods
1. `testCreateUser()` - Creates user and captures ID
2. `testUpdateUser()` - Updates user job using captured ID
3. `testGetUser()` - Retrieves user details and verifies update
4. `testDeleteUser()` - Deletes the user
5. `testVerifyDeletion()` - Verifies user is not found after deletion

### Complete Workflow Test
- `testCompleteUserWorkflow()` - End-to-end test covering all operations in sequence

## Setup and Execution

### Prerequisites
- Java 21 or higher
- Maven 3.6 or higher

### Running Tests

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd AAIB-PROJECT
   ```

2. **Run tests with Maven**
   ```bash
   mvn clean test
   ```

3. **Generate Allure Report**
   ```bash
   mvn allure:report
   ```

4. **Open Allure Report**
   ```bash
   mvn allure:serve
   ```

### Alternative Test Execution

Run specific test methods:
```bash
mvn test -Dtest=AAIB#testCreateUser
```

Run with detailed output:
```bash
mvn test -Dtest=AAIB -Dmaven.test.failure.ignore=true
```

## Test Results and Reporting

### Allure Report Features
- **Test Execution Summary**: Overview of passed/failed tests
- **Detailed Test Steps**: Step-by-step execution details
- **Request/Response Logs**: API call details and responses
- **Test Categories**: Organized by Epic, Feature, Story, and Severity
- **Timeline View**: Test execution timeline
- **Trends**: Historical test execution trends

### Report Location
- **Results**: `target/allure-results/`
- **Generated Report**: `target/allure-report/`

## API Endpoints Tested

| Method | Endpoint | Description | Expected Status |
|--------|----------|-------------|-----------------|
| POST | /api/users | Create new user | 201 |
| PUT | /api/users/{id} | Update user | 200 |
| GET | /api/users/{id} | Get user details | 200 |
| DELETE | /api/users/{id} | Delete user | 204 |
| GET | /api/users/{id} | Verify deletion | 404 |

## Key Implementation Details

### Data Extraction Pattern
```java
// Extract ID from POST response
int userId = response.jsonPath().getInt("id");

// Use extracted ID in subsequent requests
.put("/api/users/" + userId)
```

### Assertion Strategy
- **Status Code Validation**: Ensures correct HTTP response codes
- **Response Body Validation**: Verifies data integrity and updates
- **Error Response Validation**: Confirms proper error handling (404 for deleted users)

### Test Organization
- **Priority-based Execution**: Uses TestNG @Test(priority) for proper sequence
- **Allure Annotations**: @Epic, @Feature, @Story, @Description, @Severity for reporting
- **Setup Method**: @BeforeClass for base URI configuration

## Assessment Compliance

This implementation fully satisfies the Section 3 requirements:

✅ **Complete User Management Workflow**: All 5 required operations implemented  
✅ **Data Extraction**: ID captured from POST response and used in subsequent requests  
✅ **Popular Framework**: Java with Rest Assured and TestNG  
✅ **Approach Documentation**: Comprehensive README with tools and approach explanation  
✅ **Test Reporting**: Allure integration for detailed test execution results  
✅ **GitHub Repository**: Ready for repository hosting with complete source code  

## Troubleshooting

### Common Issues

1. **Maven Dependencies**: Run `mvn clean install` if dependency issues occur
2. **Java Version**: Ensure Java 21 is installed and JAVA_HOME is set correctly
3. **Network Issues**: Verify internet connectivity for reqres.in API access
4. **Allure Report**: Install Allure command-line tool if report generation fails

### Debug Mode
Run tests with debug output:
```bash
mvn test -X -Dtest=AAIB
```

## Future Enhancements

- **Data-Driven Testing**: Support for multiple test data sets
- **Parallel Execution**: TestNG parallel execution for faster test runs
- **CI/CD Integration**: GitHub Actions or Jenkins pipeline integration
- **Performance Testing**: Response time validation
- **Security Testing**: Authentication and authorization scenarios
