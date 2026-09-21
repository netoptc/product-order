package com.netoptc.productorder.e2e;

import io.restassured.http.ContentType;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class CategoryE2ETests {

    private Integer existingCategoryId, nonExistingCategoryId;
    private String clientUsername, clientPassword, adminUsername, adminPassword;
    private String adminToken, clientToken, invalidToken;


    @BeforeEach
    public void setup() throws JSONException {
        baseURI = "http://localhost:8080";
        existingCategoryId = 1;

        adminUsername = "alex@gmail.com";
        adminPassword = "123456";
        clientUsername = "maria@gmail.com";
        clientPassword = "123456";

        adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);
        clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);
        invalidToken = adminToken + "xpto";

    }


    @Test
    public void findAllShouldReturnCategoryList() {
        given()
                .get("/categories/")
                .then()
                .statusCode(200)
                .body("content.empty", is(false))
                .body("id[0]", is(1))
                .body("name[0]", equalTo("Livros"));
    }


    @Test
    public void findByIdShouldReturnCategoryWhenIdExists() {
        given()
                .get("/categories/{existingCategoryId}", existingCategoryId)
                .then()
                .statusCode(200)
                .body("id", is(existingCategoryId));
    }

    @Test
    public void insertShouldReturnSuccessWhenValidName() {
        Map<String, Object> categoryBody = new HashMap<>();
        categoryBody.put("name", "category test");

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(categoryBody)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/categories/")
                .then()
                .statusCode(201)
                .body("name", equalTo("category test"));
    }

    @Test
    public void insertShouldReturnBadRequestWhenInvalidName() {
        Map<String, Object> categoryBody = new HashMap<>();
        categoryBody.put("name", "category!@#$");

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .body(categoryBody)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/categories/")
                .then()
                .statusCode(400);
    }


    @Test
    public void insertShouldReturnForbiddenWhenClientLogged() {
        Map<String, Object> categoryBody = new HashMap<>();
        categoryBody.put("name", "category test");

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + clientToken)
                .body(categoryBody)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/categories/")
                .then()
                .statusCode(403);
    }


    @Test
    public void insertShouldReturnUnauthorizedWhenInvalidToken() {
        Map<String, Object> categoryBody = new HashMap<>();
        categoryBody.put("name", "category test");

        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + invalidToken)
                .body(categoryBody)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post("/categories/")
                .then()
                .statusCode(401);
    }

}
