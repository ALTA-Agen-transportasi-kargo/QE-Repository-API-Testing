package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class ConfirmOrderStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send POST request to confirm order with id: {int}")
    public void adminSendPOSTRequestToConfirmOrderWithId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.adminConfirmOrder(id, bringeee.getToken());
    }

    @And("order status changed to {string}")
    public void orderStatusChangedTo(String status) {
        restAssuredThat(response -> response.assertThat().body("data.status", equalToIgnoringCase(status)));
    }

    @When("admin send POST request to confirm order with id {int}")
    public void adminSendPOSTRequestToConfirmOrderWithIdId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.adminConfirmOrder(id, bringeee.getToken());
    }

    @When("admin send POST request to confirm order with id {string}")
    public void adminSendPOSTRequestToConfirmOrderWithIdId(String id) throws Exception {
        bringeee.setToken("admin");
        bringeee.adminConfirmOrder(id, bringeee.getToken());
    }

    @When("{string} send POST request to confirm order for id {int}")
    public void sendPOSTRequestToConfirmOrderForId(String role, int id) throws Exception {
        bringeee.setToken(role);
        bringeee.adminConfirmOrder(id, bringeee.getToken());
    }
}
