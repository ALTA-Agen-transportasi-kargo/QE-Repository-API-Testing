package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class DriverOrderListStepDefinitions {

    bringeeeAPI bringeee = new bringeeeAPI();

    @When("driver send GET request order list")
    public void driverSendGETRequestOrderList() throws Exception {
        bringeee.setToken("driver4");
        bringeee.driverOrderList(bringeee.getToken());
    }

    @And("all the order is matched with driver's truck id")
    public void allTheOrderIsMatchedWithDriverSTruckId() throws Exception {
//        login as driver4
        bringeee.setToken("driver4");

//        mengambil data truck type driver4
        bringeee.authMe(bringeee.getToken());
        String truck_type = lastResponse().getBody().jsonPath().getString("data.user.truck_type.truck_type");

//        mengambil truck type dari list order
        bringeee.driverOrderList(bringeee.getToken());
        JSONObject responseJson = new JSONObject(lastResponse().asString());
        JSONArray data_array = responseJson.getJSONArray("data");

        for(int i = 0; i < data_array.length(); i++) {
            String order_truck_type = "data["+i+"].truck_type.truck_type";

//            mencocokan truck type driver4 dan list order
            restAssuredThat(response -> response.assertThat().body(order_truck_type, equalTo(truck_type)));
        }

    }

    @When("{string} send GET request driver order list")
    public void sendGETRequestDriverOrderList(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.driverOrderList(bringeee.getToken());
    }
}
