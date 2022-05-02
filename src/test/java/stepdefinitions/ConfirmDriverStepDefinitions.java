package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class ConfirmDriverStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send POST request to confirm driver with id {int}")
    public void adminSendPOSTRequestToConfirmDriverWithId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.confirmDriverId(id, bringeee.getToken());
    }

    @When("admin send POST request to confirm driver with id {string}")
    public void adminSendPOSTRequestToConfirmDriverWithIdId(String id) throws Exception {
        bringeee.setToken("admin");
        bringeee.confirmDriverId(id, bringeee.getToken());
    }

    @When("{string} send POST request to confirm driver")
    public void sendPOSTRequestToConfirmDriver(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.confirmDriverId(1, bringeee.getToken());
    }
}
