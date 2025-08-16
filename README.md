# AAIB RestAssured API Testing

This project demonstrates how to test a simple REST API using **Java**, **RestAssured**, and **TestNG**.  
It includes test cases for:
- `GET` request
- `POST` request
- `DELETE` request

---

## 🛠️ Prerequisites
Make sure you have the following installed:
- Java 8+  
- Maven or Gradle  
- TestNG  
- RestAssured dependency  

Add RestAssured and JSON Simple dependencies in your **`pom.xml`** (if using Maven):

```xml
<dependencies>
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.4.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.10.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>com.googlecode.json-simple</groupId>
        <artifactId>json-simple</artifactId>
        <version>1.1.1</version>
    </dependency>
</dependencies>
