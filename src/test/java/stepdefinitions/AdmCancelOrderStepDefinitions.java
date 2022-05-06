package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class AdmCancelOrderStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send POST request to cancel order with id {int}")
    public void adminSendPOSTRequestToCancelOrderWithId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.adminCancelOrder(id, bringeee.getToken());
    }

    @When("admin send POST request to cancel order with id {string}")
    public void adminSendPOSTRequestToCancelOrderWithId(String id) throws Exception {
        bringeee.setToken("admin");
        bringeee.adminCancelOrder(id, bringeee.getToken());
    }

    @When("{string} send POST request to cancel order for id {int}")
    public void sendPOSTRequestToCancelOrderForId(String role, int id) throws Exception {
        bringeee.setToken(role);
        bringeee.adminCancelOrder(id, bringeee.getToken());
    }

    @And("order status is changed to {string}")
    public void orderStatusIsChangedTo(String arg0) {

    }

    @And("order status with id {int} is changed to {string}")
    public void orderStatusWithIdIsChangedTo(int id, String status) throws Exception {
        bringeee.setToken("admin");
        bringeee.adminOrderDetail(id, bringeee.getToken());

        restAssuredThat(response -> response.assertThat().body("data.status", equalToIgnoringCase(status)));
    }
}
