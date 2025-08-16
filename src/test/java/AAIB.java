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
    public String originalJob = "Doctor";
    public String updatedJob = "Senior Doctor";

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

    @Test
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

    @Test
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

    @Test
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

    @Test
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
