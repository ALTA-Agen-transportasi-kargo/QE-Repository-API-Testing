package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class DriverTakeOrdersStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("driver send POST request to take an order with id {int}")
    public void driverSendPOSTRequestToTakeAnOrderWithId(int id) throws Exception {
        bringeee.setToken("driver4");
        bringeee.driverTakeOrder(id, bringeee.getToken());
    }

    @When("driver send POST request to take an order with id {string}")
    public void driverSendPOSTRequestToTakeAnOrderWithId(String id) throws Exception {
        bringeee.setToken("driver4");
        bringeee.driverTakeOrder(id, bringeee.getToken());
    }

    @When("{string} send POST request to take an order")
    public void sendPOSTRequestToTakeAnOrder(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.driverTakeOrder(20, bringeee.getToken());
    }
}
