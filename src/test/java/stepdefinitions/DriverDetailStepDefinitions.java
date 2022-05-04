package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class DriverDetailStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send GET request to show driver detail for id {int}")
    public void adminSendGETRequestToShowDriverDetailForId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.getDriverDetail(id, bringeee.getToken());
    }

    @When("admin send GET request to show driver detail for id {string}")
    public void adminSendGETRequestToShowDriverDetailForId(String id) throws Exception {
        bringeee.setToken("admin");
        bringeee.getDriverDetail(id, bringeee.getToken());
    }

    @When("{string} send GET request to show driver detail for id 4")
    public void sendGETRequestToShowDriverDetailForId(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.getDriverDetail(4, bringeee.getToken());
    }

    @And("response data id should be match with {int} as requested")
    public void responseDataIdShouldBeMatchWithRequested(int id) {
        restAssuredThat(response -> response.body("data.id", equalTo(id)));
    }
}
