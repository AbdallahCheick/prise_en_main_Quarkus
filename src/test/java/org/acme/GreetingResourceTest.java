package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is; 

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void Hello() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    public void Resolution() {
        given()
                .queryParam("nbre1", 1)
                .queryParam("nbre2", 4)
                .queryParam("nbre3", 3)
                .when().get("/hello/equation")
                .then()
                .statusCode(200)
                .body("sol1", is("-1.0"))
                .body("sol2", is("-3.0"))
                .body("code", is("1"));
    }
    @Test
    public void Somme(){
        given()
            .queryParam("n1" , "10")
            .queryParam("n2" , "5")
            .when().get("/hello/somme")
            .then()
            .statusCode(200)
            .body(is("5"));
    }

        @Test
    public void Plaque(){
        given()
            .formParam("plaque" , "1425ML01")
            .when().post("/hello/plaque")
            .then()
            .statusCode(200)
            .body("message", is("La plaque est conforme."))
            .body("code", is("200"));
    }

}