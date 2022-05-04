package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class DriverListStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send GET request to show driver list")
    public void adminSendGETRequestToShowDriverList() throws Exception {
        bringeee.setToken("admin");
        bringeee.getDriverList(bringeee.getToken());
    }

    @When("{string} send GET request to show driver list")
    public void actorSendGETRequestToShowDriverList(String actor) throws Exception {
        bringeee.setToken(actor);
        bringeee.getDriverList(bringeee.getToken());
    }
}
