package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class DriverCurrentOrderStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("driver send GET request current order")
    public void driverSendGETRequestCurrentOrder() throws Exception {
        bringeee.setToken("driver4");
        bringeee.driverCurrentOrder(bringeee.getToken());
    }

    @When("{string} send GET request driver current order")
    public void sendGETRequestDriverCurrentOrder(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.driverCurrentOrder(bringeee.getToken());
    }
}
