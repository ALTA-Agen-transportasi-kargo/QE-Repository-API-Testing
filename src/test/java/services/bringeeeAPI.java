package services;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import org.checkerframework.checker.units.qual.C;


import java.io.File;
import java.util.Objects;

import static net.serenitybdd.rest.SerenityRest.*;

public class bringeeeAPI {
    private static final String BASE_URL = "https://aws.wildani.tech";

    private String bearerToken;
//    Getters and Setters
    public String getToken() {
        return bearerToken;
    }

    public void setToken(String role) throws Exception {
        String email;
        String password;

        switch (role) {
            case "customer":
                email = "budi@mail.com";
                password = "budi";
                break;
            case "driver":
                email = "driver1@mail.com";
                password = "driver1";
                break;
            case "admin":
                email = "admin@mail.com";
                password = "admin";
                break;
            case "noLogin":
                email = "";
                password = "";
                break;
            case "customer2":
                email = "bunga@mail.com";
                password = "bunga";
                break;
            case "akun_test":
                email = "buattest@qe.alterra";
                password = "test123";
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
            int responseCode = responsePostMethod.statusCode();

        if(responseCode != 200) {
            this.bearerToken = "null";
        } else {
            this.bearerToken = JsonPath.from(jsonString).get("data.token");
        }
    }


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

    public void authMe(String token) {
        if(token.equalsIgnoreCase("null")) {
            SerenityRest.given()
                    .get(BASE_URL+"/api/auth/me");
        } else {
           SerenityRest.given()
                    .header("Authorization", "Bearer "+token)
                    .get(BASE_URL+"/api/auth/me");
        }
        System.out.println(token);

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

//    Registration API
    public void registerCustomer(String Case) throws Exception {
//        form yg dibutuhin buat daftar:
        String email = "";
        String password = "";
        String name = "";
        String dob;
        String gender = "";
        String address;
        String phone_number;

//        pengkondisian berdasarkan kasus
        switch (Case) {
            case "normal":
                email = "automatets@test.qa";
                password = "testqa";
                name = "for test purposes";
                dob = "1990-01-01";
                gender = "male";
                address = "test address";
                phone_number = "081234567890";
            break;
            case "without_email_password":
                name = "contoh qa";
                dob = "1990-01-01";
                gender = "male";
                address = "Contoh alamat";
                phone_number = "081234567890";
            break;
            case "without_name_gender":
                email = "qa@test.com";
                password = "testaja";
                dob = "1990-01-01";
                address = "Contoh alamat";
                phone_number = "081234567890";
            break;
            case "with_registered_email":
                email = "budi@mail.com";
                password = "testaja";
                name = "budi kali ya";
                dob = "1990-01-01";
                gender = "suka-suka budi";
                address = "Contoh alamat";
                phone_number = "081234567890";
            break;
            default:
                throw new Exception("no such case: "+Case);
        }

//        POST method
        SerenityRest.given().contentType("multipart/form-data")
                .multiPart("email",email)
                .multiPart("password",password)
                .multiPart("name",name)
                .multiPart("dob",dob)
                .multiPart("gender",gender)
                .multiPart("address",address)
                .multiPart("phone_number",phone_number)
                .post(BASE_URL+"/api/customers");

    }

    public void registerDriver(String Case) throws Exception {
        String email;
        String password;
        String name;
        String dob;
        String gender;
        String address;
        String phone_number;
        File avatar;
        int truck_type_id;
        File ktp_file;
        File stnk_file = null;
        File driver_license_file;
        int age;
        String vehicle_identifier;
        String nik;
        File vehicle_picture;

//        kondisi
        switch (Case) {
            case "normal":
                email = "automatest@test.qa";
                password = "testqa";
                name = "for test purposes";
                dob = "1990-01-01";
                gender = "male";
                address = "test address";
                phone_number = "081234567890";
                avatar = new File("src/test/resources/upload/avatar.jpg");
                truck_type_id = 1;
                ktp_file = new File("src/test/resources/upload/ktp.jpg");
                stnk_file = new File("src/test/resources/upload/stnk.jpg");
                driver_license_file = new File("src/test/resources/upload/driver_license.jpg");
                age = 32;
                vehicle_identifier = "PLAT NOMOR";
                nik = "1234567890123456";
                vehicle_picture = new File("src/test/resources/upload/vehicle_picture.jpg");
            break;
            case "without_email_password":
                email = "";
                password = "";
                name = "for test purposes";
                dob = "1990-01-01";
                gender = "male";
                address = "test address";
                phone_number = "081234567890";
                avatar = new File("src/test/resources/upload/avatar.jpg");
                truck_type_id = 1;
                ktp_file = new File("src/test/resources/upload/ktp.jpg");
                stnk_file = new File("src/test/resources/upload/stnk.jpg");
                driver_license_file = new File("src/test/resources/upload/driver_license.jpg");
                age = 32;
                vehicle_identifier = "PLAT NOMOR";
                nik = "1234567890123456";
                vehicle_picture = new File("src/test/resources/upload/vehicle_picture.jpg");
                break;
            case "without_stnk":
                email = "test@test.qa";
                password = "testqa";
                name = "for test purposes";
                dob = "1990-01-01";
                gender = "male";
                address = "test address";
                phone_number = "081234567890";
                avatar = new File("src/test/resources/upload/avatar.jpg");
                truck_type_id = 1;
                ktp_file = new File("src/test/resources/upload/ktp.jpg");
                driver_license_file = new File("src/test/resources/upload/driver_license.jpg");
                age = 32;
                vehicle_identifier = "PLAT NOMOR";
                nik = "1234567890123456";
                vehicle_picture = new File("src/test/resources/upload/vehicle_picture.jpg");
                break;
            case "with_registered_email":
                email = "ahmad@mail.com";
                password = "testqa";
                name = "for test purposes";
                dob = "1990-01-01";
                gender = "male";
                address = "test address";
                phone_number = "081234567890";
                avatar = new File("src/test/resources/upload/avatar.jpg");
                truck_type_id = 1;
                ktp_file = new File("src/test/resources/upload/ktp.jpg");
                stnk_file = new File("src/test/resources/upload/stnk.jpg");
                driver_license_file = new File("src/test/resources/upload/driver_license.jpg");
                age = 32;
                vehicle_identifier = "PLAT NOMOR";
                nik = "1234567890123456";
                vehicle_picture = new File("src/test/resources/upload/vehicle_picture.jpg");
                break;
            default:
                throw new Exception("no such case: "+Case);
        }
        if(Case.equalsIgnoreCase("without_stnk")) {
            SerenityRest.given().contentType("multipart/form-data")
                    .multiPart("email",email)
                    .multiPart("password",password)
                    .multiPart("name",name)
                    .multiPart("dob",dob)
                    .multiPart("gender",gender)
                    .multiPart("address",address)
                    .multiPart("phone_number",phone_number)
                    .multiPart("avatar",avatar)
                    .multiPart("truck_type_id",truck_type_id)
                    .multiPart("ktp_file", ktp_file)
                    .multiPart("driver_license_file", driver_license_file)
                    .multiPart("age",age)
                    .multiPart("vehicle_identifier", vehicle_identifier)
                    .multiPart("nik",nik)
                    .multiPart("vehicle_picture", vehicle_picture)
                    .post(BASE_URL+"/api/drivers");
        } else {
            SerenityRest.given().contentType("multipart/form-data")
                    .multiPart("email",email)
                    .multiPart("password",password)
                    .multiPart("name",name)
                    .multiPart("dob",dob)
                    .multiPart("gender",gender)
                    .multiPart("address",address)
                    .multiPart("phone_number",phone_number)
                    .multiPart("avatar",avatar)
                    .multiPart("truck_type_id",truck_type_id)
                    .multiPart("ktp_file", ktp_file)
                    .multiPart("stnk_file", stnk_file)
                    .multiPart("driver_license_file", driver_license_file)
                    .multiPart("age",age)
                    .multiPart("vehicle_identifier", vehicle_identifier)
                    .multiPart("nik",nik)
                    .multiPart("vehicle_picture", vehicle_picture)
                    .post(BASE_URL+"/api/drivers");
        }



    }
//    End of Feature: Registration API
//    Customer Create Order API
    public void createOrder(String condition, String token) throws Exception {
        System.out.println(token);
        //  Forms input:

        String destination_start_province = "DKI JAKARTA";
        String destination_start_city = "JAKARTA UTARA";
        String destination_start_district = "PADEMANGAN";
        String destination_start_address = "Pademangan Jakarta Utara";
        String destination_start_postal = "14420";
        String destination_start_lat = "-6.129634";
        String destination_start_long = "106.827312";
        String destination_end_province = "JAWA BARAT";
        String destination_end_city = "KARAWANG";
        String destination_end_district = "KARAWANG BARAT";
        String destination_end_address = "Karawang Barat, Karawang";
        String destination_end_postal = "41311";
        String destination_end_lat = "-6.276677";
        String destination_end_long = "107.286206";
        File order_picture = new File("src/test/resources/upload/avatar.jpg");
        String truck_type_id = "1";
        String total_volume = "200";
        String total_weight = "30";
        String description = "order coba coba";

        switch (condition) {
            case "normal":
            case "without_order_picture":
                break;
            case "without_end_destination":
                destination_end_province = "";
                destination_end_city = "";
                destination_end_district = "";
                destination_end_address = "";
            break;
            case "without_order_measurement":
                total_volume = "";
                total_weight = "";
            break;
            default:
                throw new Exception("no such condition exist: "+condition);
        }

        System.out.println(token);
//        Post Method
       if(condition.equalsIgnoreCase("without_order_picture")) {

           SerenityRest.given().contentType("multipart/form-data")
                   .header("Authorization", "Bearer " + token )
                   .multiPart("destination_start_province", destination_start_province)
                   .multiPart("destination_start_city", destination_start_city)
                   .multiPart("destination_start_district", destination_start_district)
                   .multiPart("destination_start_address", destination_start_address)
                   .multiPart("destination_start_postal", destination_start_postal)
                   .multiPart("destination_start_lat", destination_start_lat)
                   .multiPart("destination_start_long", destination_start_long)
                   .multiPart("destination_end_province", destination_end_province)
                   .multiPart("destination_end_city", destination_end_city)
                   .multiPart("destination_end_district", destination_end_district)
                   .multiPart("destination_end_address", destination_end_address)
                   .multiPart("destination_end_postal", destination_end_postal)
                   .multiPart("destination_end_lat", destination_end_lat)
                   .multiPart("destination_end_long", destination_end_long)
                   .multiPart("truck_type_id", truck_type_id)
                   .multiPart("total_volume", total_volume)
                   .multiPart("total_weight", total_weight)
                   .multiPart("description", description)
                   .post(BASE_URL+"/api/customers/orders");
           System.out.println(token);
       } else {

           SerenityRest.given().contentType("multipart/form-data")
                   .header("Authorization", "Bearer " + token)
                   .multiPart("destination_start_province", destination_start_province)
                   .multiPart("destination_start_city", destination_start_city)
                   .multiPart("destination_start_district", destination_start_district)
                   .multiPart("destination_start_address", destination_start_address)
                   .multiPart("destination_start_postal", destination_start_postal)
                   .multiPart("destination_start_lat", destination_start_lat)
                   .multiPart("destination_start_long", destination_start_long)
                   .multiPart("destination_end_province", destination_end_province)
                   .multiPart("destination_end_city", destination_end_city)
                   .multiPart("destination_end_district", destination_end_district)
                   .multiPart("destination_end_address", destination_end_address)
                   .multiPart("destination_end_postal", destination_end_postal)
                   .multiPart("destination_end_lat", destination_end_lat)
                   .multiPart("destination_end_long", destination_end_long)
                   .multiPart("order_picture", order_picture)
                   .multiPart("truck_type_id", truck_type_id)
                   .multiPart("total_volume", total_volume)
                   .multiPart("total_weight", total_weight)
                   .multiPart("description", description)
                   .post(BASE_URL+"/api/customers/orders");

       }
    }

//    End of Feature: Customer Create Order API
//    Feature: Edit Profile Customer

    public void editProfile (String condition, String token) throws Exception {
        System.out.println(token);
//        form yg dibutuhin untuk edit profile:
        String email = "";
        String password = "";
        String name = "";
        String dob = "";
        String gender = "";
        String address = "";
        String phone_number = "";

//        pengkondisian berdasarkan kasus
        switch (condition) {
            case "normal":
                name = "for test purposes";
                dob = "1990-01-01";
                gender = "male";
                address = "test address";
                phone_number = "081234567890";
                break;
            case "without_alldata":
                break;
            default:
                throw new Exception("no such condition: "+condition);
        }

//        PUT method
        SerenityRest.given().contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("email",email)
                .multiPart("password",password)
                .multiPart("name",name)
                .multiPart("dob",dob)
                .multiPart("gender",gender)
                .multiPart("address",address)
                .multiPart("phone_number",phone_number)
                .put(BASE_URL+"/api/customers");

        System.out.println(lastResponse().prettyPrint());

    }

//    End of Feature: Edit Profile Customer
//    Feature: Delete Customer

    public void deleteCustomer(String token) throws Exception {
        System.out.println(token);
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .delete(BASE_URL + "/api/customers");
    }

//    End of Feature: Delete Customer
//    Customer Order List API

    public void getOrderListBy(String parameter, String token) {
        SerenityRest.given()
                .header("Authorization", "Bearer "+token)
                .queryParam("status", parameter)
                .get(BASE_URL + "/api/customers/orders");
    }

    public void getOrderListBy(int parameter, String token) {
        SerenityRest.given()
                .header("Authorization", "Bearer "+token)
                .queryParam("status", parameter)
                .get(BASE_URL + "/api/customers/orders");
    }
//    End of Feature: Customer Order List API
//    Customer detail order API

    public void getOrderDetail(int id, String token) {
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(BASE_URL + "/api/customers/orders/"+id);

    }

    public void getOrderDetail(String id, String token) {
        SerenityRest.given()
                .header("Authorization", "Bearer " + token)
                .get(BASE_URL + "/api/customers/orders/"+id);

    }

//    end of customer detail order API



}
