

# API Automation Testing with Rest Assured & TestNG

---

## Approach Explanation

### Testing Strategy:
Your approach implements a sequential **User Management Workflow** that follows the **CRUD (Create, Read, Update, Delete)** operations pattern.  
The tests are designed to:

1. **Create** a new user and capture the generated ID  
2. **Update** the user's information using the captured ID  
3. **Verify** the update by retrieving the user details  
4. **Delete** the user  
5. **Confirm Deletion** by attempting to retrieve the deleted user  

---

### Key Implementation Features:
- **Data Extraction**: Captures the user ID from the `POST` response and reuses it in subsequent requests  
- **State Management**: Maintains test state by storing the `userId` as a class variable  
- **Sequential Execution**: Tests are designed to run in order using TestNG’s dependency mechanism  
- **Comprehensive Validation**: Validates both **status codes** and **response body content**  

---

## Tools Used

### 1. Java Programming Language
- **Version**: Java 21 (as specified in `pom.xml`)  
- **Purpose**: Core programming language for test automation  

### 2. Rest Assured Framework
- **Version**: 5.5.4  
- **Purpose**: HTTP client library for API testing  
- **Key Features Used**:  
  - `given()` → Request specification  
  - `when()` → HTTP method execution  
  - `then()` → Response validation  
  - `extract()` → Response extraction  
  - JSONPath for parsing and data extraction  

### 3. TestNG Testing Framework
- **Version**: 7.10.2  
- **Purpose**: Test execution and assertion framework  
- **Key Features Used**:  
  - `@Test` annotations for test methods  
  - `@BeforeClass` for setup configuration  
  - `Assert.assertEquals()` for validations  
  - Test prioritization and **dependent test execution**  

### 4. JSON Simple Library
- **Version**: 1.1  
- **Purpose**: JSON object creation and manipulation  
- **Usage**: Constructing request bodies for API calls  

### 5. Maven Build Tool
- **Purpose**: Dependency management and project build automation  
- **Configuration**: All dependencies defined in `pom.xml`  

---

## Technical Approach Details

### API Endpoint Strategy:
- **Base URL**: `http://localhost:3000` (local development server)  
- **Endpoints**:  
  - `POST /data` → Create user  
  - `PUT /data/{id}` → Update user  
  - `GET /data/{id}` → Retrieve user  
  - `DELETE /data/{id}` → Delete user  

---

### Data Flow:
1. **Create**: `POST` request → Extract ID from response → Store in `userId`  
2. **Update**: `PUT` request using stored `userId`  
3. **Verify**: `GET` request to confirm updated details  
4. **Delete**: `DELETE` request using stored `userId`  
5. **Confirm**: `GET` request expecting `404 Not Found`  

---

### Validation Strategy:
- **Status Code Validation**: Ensures correct HTTP response codes (`201`, `200`, `404`)  
- **Response Body Validation**: Confirms data integrity after each operation  
- **Error Handling**: Validates correct handling of deleted/non-existent resources  

---

### Test Execution Pattern:
- **Sequential**: Tests run in defined order due to data dependencies  
- **Stateful**: Maintains state across tests with shared variables (`userId`)  
- **Isolated**: Each test verifies a distinct CRUD operation  
- **Comprehensive**: Covers full CRUD lifecycle with proper assertions  

---

## Summary
This approach demonstrates a solid understanding of **API testing principles**, **data extraction techniques**, and **automated test workflow design**.  
It ensures reliability through **status code checks**, **data validation**, and **error response verification**, making it a strong API automation framework example.