package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class AdminOrderHistoriesStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send GET request order timeline with id: {int}")
    public void adminSendGETRequestOrderTimelineWithId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.getOrderHistoriesAdmin(id, bringeee.getToken());
    }

    @When("admin send GET request order timeline with invalid id: {int}")
    public void adminSendGETRequestOrderTimelineWithInvalidId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.getOrderHistoriesAdmin(id, bringeee.getToken());
    }

    @When("admin send GET request order timeline with invalid id: {string}")
    public void adminSendGETRequestOrderTimelineWithInvalidId(String id) throws Exception {
        bringeee.setToken("admin");
        bringeee.getOrderHistoriesAdmin(id, bringeee.getToken());
    }

    @When("admin send GET request order timeline")
    public void adminSendGETRequestOrderTimeline() throws Exception {
        bringeee.setToken("noLogin");
        bringeee.getOrderHistoriesAdmin(4, bringeee.getToken());
    }
}
