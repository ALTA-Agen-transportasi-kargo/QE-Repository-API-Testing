package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class CustomerDetailStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send GET request to get customer detail for id {int}")
    public void adminSendGETRequestToGetCustomerDetailForId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.getCustomerDetail(id, bringeee.getToken());
    }

    @When("admin send GET request to get customer detail for invalid id {string}")
    public void adminSendGETRequestToGetCustomerDetailForInvalidId(String id) throws Exception {
        bringeee.setToken("admin");
        bringeee.getCustomerDetail(id, bringeee.getToken());
    }

    @When("{string} send GET request to show customer detail")
    public void sendGETRequestToShowCustomerDetail(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.getCustomerDetail(3, bringeee.getToken());
    }
}
