package services;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;


import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static net.serenitybdd.rest.SerenityRest.*;

public class bringeeeAPI {
    private static final String BASE_URL = "https://aws.wildani.tech";

    public void authAs(String actor) throws Exception {

        String email = null;
        String password = null;

        switch(actor) {
            case "customer":
                email = "budi@mail.com";
                password = "budi123";
                break;
            case "driver_verified":
                email = "ahmad@mail.com";
                password = "ahmad123";
                break;
            case "driver_pending":
                email = "wawan@mail.com";
                password = "wawan123";
                break;
            case "admin":
                email = "admin@mail.com";
                password = "admin123";
                break;
            case "no_email":
                password = "budi123";
                break;
            case "no_password":
                email = "budi@mail.com";
                break;
            case "no_email_password":
                break;
            case "unregistered_email":
                email = "budi_ganteng@mail.com";
                password = "budi123";
                break;
            case "unregistered_password":
                email = "budi@mail.com";
                password = "budi-salahpassword";
                break;
            default:
                throw new Exception("no such actor: "+actor);
        }


        SerenityRest.given().config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .encodeContentTypeAs("", ContentType.URLENC)
                        )
                )
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("email",email)
                .formParam("password", password)
                .post(BASE_URL+"/api/auth");

    }

    public void postLoginWithBody(String payloadName, String payloadFolder) {
        String payloadPath = "src/test/resources/payload/"+payloadFolder+"/"+payloadName;

//        File jsonBody = new File(payloadPath);
//        System.out.println(jsonBody);

//        RestAssured.given()
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .body(jsonBody.toString())
//                .post(BASE_URL+"/api/auth");

        SerenityRest.given().config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .encodeContentTypeAs("", ContentType.URLENC)
                        )
                )
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("email","budi@mail.com")
                .formParam("password", "budi123")
                .post(BASE_URL+"/api/auth");
    }

    public void validateResponseCode(int responseCode) {

        restAssuredThat(response -> response.statusCode(responseCode));
    }

    public void validateJsonSchema(String schema, String folderSchema) {

    }

    public void usingToken(String role) {
        String tokenGenerated = null;

        RestAssured.given()
                .header("Authorization", "Bearer "+tokenGenerated)
                .get(BASE_URL+"/api/auth/me");
    }

}