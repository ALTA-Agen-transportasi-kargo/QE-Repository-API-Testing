package services;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;


import static net.serenitybdd.rest.SerenityRest.*;

public class bringeeeAPI {
    private static final String BASE_URL = "https://aws.wildani.tech";

//    Feeature: Login API

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
            case "admin":
                email = "admin@mail.com";
                password = "admin123";
                break;
            case "driver_pending":
                email = "wawan@mail.com";
                password = "wawan123";
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

    public String getBearerToken(String role) throws Exception {
        String email = null;
        String password = null;

        switch (role) {
            case "customer":
                email = "budi@mail.com";
                password = "budi123";
                break;
            case "driver":
                email = "ahmad@mail.com";
                password = "ahmad123";
                break;
            case "admin":
                email = "admin@mail.com";
                password = "admin123";
                break;
            default:
                throw new Exception("no such role: " + role);
        }

        Response responsePostMethod = given().config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .encodeContentTypeAs("", ContentType.URLENC)
                        )
                )
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("email", email)
                .formParam("password", password)
                .post(BASE_URL + "/api/auth");

        String jsonString = responsePostMethod.getBody().asString();

        return JsonPath.from(jsonString).get("data.token");
    }

    public void authMe(String token) {
        if(token.equalsIgnoreCase("null")) {
            SerenityRest.given()
                    .get(BASE_URL+"/api/auth/me");
        } else {
           SerenityRest.given()
                    .header("Authorization", "Bearer "+token)
                    .get(BASE_URL+"/api/auth/me");
        }

    }
//    End of Feature: Login API

//    Feature: Wilayah API
    public String setProvinceId(int id) {
        return BASE_URL + "/api/provinces/"+id+"/cities";
    }

    public void getAllProvinces() {
        SerenityRest.get(BASE_URL+"/api/provinces");
    }

    public void getCitiesByProvinceId(int id) {
        SerenityRest.get(BASE_URL+"/api/provinces/"+id+"/cities");
    }

    public void getCitiesByProvinceId(String id) {
        SerenityRest.get(BASE_URL+"/api/provinces/"+id+"/cities");
    }

    public void getDistrictsByCityId(int id, String url) {
        SerenityRest.get(url+"/"+id+"/districts");
    }

    public void getDistrictsByCityId(String id, String url) {
        SerenityRest.get(url+"/"+id+"/districts");
    }

//    End of Feature: Wilayah API
}
