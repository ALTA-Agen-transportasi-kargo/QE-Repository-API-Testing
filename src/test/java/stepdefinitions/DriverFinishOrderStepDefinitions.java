package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class DriverFinishOrderStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("driver send POST request to finish an order with id {int}")
    public void driverSendPOSTRequestToFinishAnOrderWithId(int id) throws Exception {
        bringeee.setToken("driver4");
        bringeee.driverFinishOrder(id, bringeee.getToken());
    }

    @When("driver send POST request to finish an order with id {string}")
    public void driverSendPOSTRequestToFinishAnOrderWithId(String id) throws Exception {
        bringeee.setToken("driver4");
        bringeee.driverFinishOrder(id, bringeee.getToken());
    }

    @When("{string} send POST request to finish an order")
    public void sendPOSTRequestToFinishAnOrder(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.driverFinishOrder(20, bringeee.getToken());
    }
}
