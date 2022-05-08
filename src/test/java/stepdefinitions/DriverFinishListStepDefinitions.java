package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class DriverFinishListStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("driver send GET request finish order list")
    public void driverSendGETRequestFinishOrderList() throws Exception {
        bringeee.setToken("driver4");
        bringeee.driverFinishedOrders(bringeee.getToken());
    }

    @When("{string} send GET request driver finish order list")
    public void sendGETRequestDriverFinishOrderList(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.driverFinishedOrders(bringeee.getToken());
    }
}
