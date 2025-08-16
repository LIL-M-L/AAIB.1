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
        RestAssured.baseURI = "http://localhost:3000";  // Local API base URL
    }

    @Test
    public void testCreateUser() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Manar Aziz");
        requestBody.put("job", originalJob);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post("/data")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 201, "User creation should return 201");
        userId = String.valueOf(response.jsonPath().getString("id"));

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
                .body(updateBody.toString())
                .when()
                .put("/data/" + userId)
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.jsonPath().getString("job"), updatedJob);
        Assert.assertEquals(response.jsonPath().getString("name"), "Manar Aziz");
        Assert.assertEquals(response.statusCode(), 200, "User update should return 200");

        System.out.println("User updated successfully: " + response.asString());
        System.out.println("User ID: " + userId);
        System.out.println("Updated user with ID: " + userId);
        System.out.println("Updated user with name: " + updatedJob);

    }

    @Test(dependsOnMethods = "testUpdateUser")
    public void testGetUserAfterUpdate() {
        Response response = given()
                .when()
                .get("/data/" + userId)
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200, "Get user should return 200");
        Assert.assertEquals(response.jsonPath().getString("job"), updatedJob, "Job should be updated");

        System.out.println("Verified updated user: " + response.asString());
    }

    @Test(dependsOnMethods = "testGetUserAfterUpdate")
    public void testDeleteUser() {
        Response response = given()
                .when()
                .delete("/data/" + userId)
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 200, "User deletion should return 200");

        System.out.println("User deleted successfully");
    }

    @Test(dependsOnMethods = "testDeleteUser")
    public void testVerifyUserDeletion() {
        Response response = given()
                .when()
                .get("/data/" + userId)
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 404, "Deleted user should return 404");

        System.out.println("Verified user deletion (404)");
        Assert.assertTrue(response.getTime() < 2000, "API response should be < 2s");

    }
}
