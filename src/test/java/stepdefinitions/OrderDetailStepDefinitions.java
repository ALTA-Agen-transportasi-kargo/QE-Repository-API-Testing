package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class OrderDetailStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("customer send GET request to show order detail for id {int}")
    public void customerSendGETRequestToShowOrderDetailForId(int id) throws Exception {
        bringeee.setToken("customer");
        bringeee.getOrderDetail(id, bringeee.getToken());
    }

    @When("customer send GET request to show order detail for id {string}")
    public void customerSendGETRequestToShowOrderDetailForId(String id) throws Exception {
        bringeee.setToken("customer");
        bringeee.getOrderDetail(id, bringeee.getToken());
    }

    @When("customer send GET request to show order detail")
    public void customerSendGETRequestToShowOrderDetail() throws Exception {
        bringeee.setToken("noLogin");
        bringeee.getOrderDetail(4, bringeee.getToken());
    }
}
