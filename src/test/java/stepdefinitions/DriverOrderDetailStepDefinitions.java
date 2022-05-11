package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class DriverOrderDetailStepDefinitions {
    bringeeeAPI bringeee =  new bringeeeAPI();

    @When("driver send GET request order detail for id {int}")
    public void driverSendGETRequestOrderDetailForId(int id) throws Exception {
        bringeee.setToken("driver4");
        bringeee.adminOrderDetail(id, bringeee.getToken());
    }

    @When("driver send GET request order detail for id {string}")
    public void driverSendGETRequestOrderDetailForId(String id) throws Exception {
        bringeee.setToken("driver4");
        bringeee.adminOrderDetail(id, bringeee.getToken());
    }
}
