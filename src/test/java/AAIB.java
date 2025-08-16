import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AAIB {

    @Test
    public void testGet() {
        RestAssured.baseURI = "http://localhost:3000"; // No trailing slash here

        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/data") // Relative path
                .then()
                .extract()
                .response();

        // Print raw body
        System.out.println("Response Body:" + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
    }
    @Test
    public void testPost() {
        RestAssured.baseURI = "http://localhost:3000";
        JSONObject request = new JSONObject();
        request.put("email", "manar@gmail.com");
        request.put("job", "Doctor");
        System.out.println("Request Body:" + request.toString());
        Response response =
                given().
                        contentType(ContentType.JSON).
                        body(request.toString()).
                        when().post("/data").then().extract().response();

        System.out.println("Response Body:" + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
    }
    @Test
    public void testDelete() {
        RestAssured.baseURI = "http://localhost:3000";

        // Suppose your user ID is 1 (you may need to capture the ID from POST response)
        int userId = 1;

        Response response = given()
                .when()
                .delete("/data/" + userId) // DELETE /data/1
                .then()
                .extract()
                .response();

        System.out.println("Response Body: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());
    }


}
