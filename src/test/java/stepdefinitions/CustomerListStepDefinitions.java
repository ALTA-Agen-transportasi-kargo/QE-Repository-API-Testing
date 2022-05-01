package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class CustomerListStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send GET request to show customer list")
    public void adminSendGETRequestToShowCustomerList() throws Exception {
        bringeee.setToken("admin");
        bringeee.getCustomerList("", bringeee.getToken());
    }

    @When("admin send GET request to show customer list with filter name {string}")
    public void adminSendGETRequestToShowCustomerListWithFilterName(String filter) throws Exception {
        bringeee.setToken("admin");
        bringeee.getCustomerList(filter, bringeee.getToken());
    }

    @And("result customer name should contain {string}")
    public void customerNameShouldContain(String name) {

        JSONObject object = new JSONObject(lastResponse().asString());
        JSONArray data = object.getJSONArray("data");

        String roleKey;

        for (int i = 0; i < data.length(); i++) {
            roleKey = "data[" + i + "].name";
            String finalRoleKey = roleKey;
            restAssuredThat(response -> response.body(finalRoleKey, containsString(name)));
        }

    }

    @When("{string} send GET request to show customer list")
    public void sendGETRequestToShowCustomerList(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.getCustomerList("", bringeee.getToken());
    }

    @And("all result role is {string}")
    public void allResultRoleIs(String role) {
        JSONObject object = new JSONObject(lastResponse().asString());
        JSONArray data = object.getJSONArray("data");

        String roleKey;

        for (int i = 0; i < data.length(); i++) {
            roleKey = "data[" + i + "].role";
            String finalRoleKey = roleKey;
            restAssuredThat(response -> response.body(finalRoleKey, equalTo(role)));
        }


    }
}
