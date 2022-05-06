package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class AdmListOrderStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send GET request all orders")
    public void adminSendGETRequestAllOrders() throws Exception {
        bringeee.setToken("admin");
        bringeee.adminOrderListByStatus("", bringeee.getToken());
    }

    @When("admin send GET request orders with status {string}")
    public void adminSendGETRequestOrdersWithStatus(String filter) throws Exception {
        bringeee.setToken("admin");
        bringeee.adminOrderListByStatus(filter, bringeee.getToken());
    }

    @When("{string} send GET request all orders")
    public void sendGETRequestAllOrders(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.adminOrderListByStatus("", bringeee.getToken());
    }

    @And("All order status should be {string}")
    public void allOrderStatusShouldBe(String filter) {
        JSONObject jsonResponse = new JSONObject(lastResponse().asString());
        JSONArray data = jsonResponse.getJSONArray("data");

        String status;

        for(int i = 0; i < data.length(); i++) {
            status = "data["+i+"].status";
            String statusFinal = status;

            restAssuredThat(response -> response.assertThat().body(statusFinal, equalToIgnoringCase(filter)));
        }

    }
}
