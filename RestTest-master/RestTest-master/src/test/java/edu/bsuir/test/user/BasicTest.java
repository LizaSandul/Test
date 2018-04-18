package edu.bsuir.test.user;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;

public abstract class BasicTest {

    private static RequestSpecification spec;

    @BeforeClass
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080/SpringBootRestApi/api/")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    protected String createResource(String path, Object bodyPayload) {
        return given()
                .spec(spec)
                .body(bodyPayload)
                .when()
                .post(path)
                .then()
                .statusCode(201)
                .extract().header("location");
    }


    protected <T> T getResource(String locationHeader, Class<T> responseClass) {
        return given()
                .spec(spec)
                .when()
                .get(locationHeader)
                .then()
                .statusCode(200)
                .extract().as(responseClass);
    }

    protected int deleteResource(String path){
        return given()
                .spec(spec)
                .when()
                .delete(path)
                .then()
                .extract().statusCode();
    }

    protected void deleteAllResources(String path){
        given()
                .spec(spec)
                .when()
                .delete(path);
    }

    protected String getAllResources(String path){
        return  given()
                .spec(spec)
                .when()
                .get(path)
                .then()
                .extract().body().asString();
    }

    protected String updateResource(String path, Object bodyPayload) {
        return given()
                .spec(spec)
                .body(bodyPayload)
                .when()
                .put(path)
                .then()
                .statusCode(200)
                .extract().header("location");
    }


}
