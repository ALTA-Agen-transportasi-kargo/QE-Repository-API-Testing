package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class CustCancelOrderStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("customer send POST request to cancel order with id {int}")
    public void customerSendPOSTRequestToCancelOrderWithId(int id) throws Exception {
        bringeee.setToken("customer");
        bringeee.custCancelOrder(id, bringeee.getToken());
    }

    @When("customer send POST request to cancel order with id {string}")
    public void customerSendPOSTRequestToCancelOrderWithId(String id) throws Exception {
        bringeee.setToken("customer");
        bringeee.custCancelOrder(id, bringeee.getToken());
    }

    @When("{string} send POST request to cancel order with id {int}")
    public void sendPOSTRequestToCancelOrderWithId(String role, int id) throws Exception {
        bringeee.setToken(role);
        bringeee.custCancelOrder(id, bringeee.getToken());
    }
}
