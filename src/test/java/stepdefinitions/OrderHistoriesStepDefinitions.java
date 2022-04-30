package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class OrderHistoriesStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("customer send GET request order timeline with id: {int}")
    public void customerSendGETRequestOrderTimelineWithId(int id) throws Exception {
        bringeee.setToken("customer");
        bringeee.getOrderHistories(id, bringeee.getToken());
    }

    @When("customer send GET request order timeline with invalid id: {int}")
    public void customerSendGETRequestOrderTimelineWithInvalidId(int id) throws Exception {
        bringeee.setToken("customer");
        bringeee.getOrderHistories(id, bringeee.getToken());
    }

    @When("customer send GET request order timeline with invalid id: {string}")
    public void customerSendGETRequestOrderTimelineWithInvalidId(String id) throws Exception {
        bringeee.setToken("customer");
        bringeee.getOrderHistories(id, bringeee.getToken());
    }

    @When("customer send GET request order timeline")
    public void customerSendGETRequestOrderTimeline() throws Exception {
        bringeee.setToken("noLogin");
        bringeee.getOrderHistories(4, bringeee.getToken());
    }
}
