import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AAIB {

    public int userId;
    public String originalJob = "Doctor";
    public String updatedJob = "Senior Doctor";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:3000";  // Local API base URL
    }

    @Test(priority = 1)
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
        userId = response.jsonPath().getInt("id");
        Assert.assertTrue(userId > 0, "User ID should be present in response");

        Assert.assertEquals(response.jsonPath().getString("name"), "Manar Aziz");
        Assert.assertEquals(response.jsonPath().getString("job"), originalJob);

        System.out.println("Created user with ID: " + userId);
    }

    @Test(priority = 2)
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

        Assert.assertEquals(response.getStatusCode(), 200, "User update should return 200");
        Assert.assertEquals(response.jsonPath().getString("job"), updatedJob);

        System.out.println("User updated successfully: " + response.asString());
    }

    @Test(priority = 3)
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

    @Test(priority = 4)
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

    @Test(priority = 5)
    public void testVerifyUserDeletion() {
        Response response = given()
                .when()
                .get("/data/" + userId)
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.getStatusCode(), 404, "Deleted user should return 404");

        System.out.println("Verified user deletion (404)");
    }
}
