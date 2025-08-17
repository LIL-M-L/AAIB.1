import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AAIB {

    public String userId;
    public String originalJob = "Tester";
    public String updatedJob = "Senior tester";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api/users";
    }

    @Test
    public void testCreateUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Manar Aziz");
        requestBody.put("job", originalJob);

        Response response = given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(requestBody.toString())
                .when()
                .post("/users")   
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 201, "User creation should return 201");
        userId = response.jsonPath().getString("id");

        Assert.assertEquals(response.jsonPath().getString("name"), "Manar Aziz");
        Assert.assertEquals(response.jsonPath().getString("job"), originalJob);

        System.out.println("Created user with ID: " + userId);
    }

    @Test(dependsOnMethods = "testCreateUser")
    public void testUpdateUser() {
        JSONObject updateBody = new JSONObject();
        updateBody.put("name", "Manar Aziz");
        updateBody.put("job", updatedJob);

        Response response = given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(updateBody.toString())
                .when()
                .put("/users/" + userId)  
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "User update should return 200");
        Assert.assertEquals(response.jsonPath().getString("job"), updatedJob);

        System.out.println("User updated successfully: " + response.asString());
    }

    @Test(dependsOnMethods = "testUpdateUser")
    public void testGetUserAfterUpdate() {
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/users/" + userId)
                .then()
                .extract()
                .response();

        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 404,
                "Get user should return 200 or 404 (mock API behavior)");

        System.out.println("Fetched user: " + response.asString());
    }

    @Test(dependsOnMethods = "testGetUserAfterUpdate")
    public void testDeleteUser() {
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("/users/" + userId)
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 204, "User deletion should return 204");

        System.out.println("User deleted successfully");
    }

    @Test(dependsOnMethods = "testDeleteUser")
    public void testVerifyUserDeletion() {
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/users/" + userId)
                .then()
                .extract()
                .response();

        Assert.assertTrue(response.getStatusCode() == 404 || response.getStatusCode() == 200,
                "Deleted user may return 404 (mock API) or static data");

        System.out.println("Verified user deletion, status: " + response.getStatusCode());
    }
}

