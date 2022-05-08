package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class CustConfirmOrderStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();


    @And("order id {int} status is {string}")
    public void orderIdStatusIs(int id, String status) throws Exception {
        bringeee.setToken("customer");
        bringeee.getOrderDetail(id, bringeee.getToken());

        restAssuredThat(response -> response.assertThat().body("data.status", equalToIgnoringCase(status)));
    }

    @When("customer send POST request to confirm order with id {int}")
    public void customerSendPOSTRequestToConfirmOrderWithId(int id) throws Exception {
        bringeee.setToken("customer");
        bringeee.custConfirmOrder(id, bringeee.getToken());
    }

    @SneakyThrows
    @And("order id {int} status should change to {string}")
    public void orderStatusShouldChangeTo(int id, String status) throws Exception {
        bringeee.setToken("customer");
        bringeee.getOrderDetail(id, bringeee.getToken());

        restAssuredThat(response -> response.assertThat().body("data.status", equalToIgnoringCase(status)));
    }

    @When("customer send POST request to confirm order with id {string}")
    public void customerSendPOSTRequestToConfirmOrderWithId(String id) throws Exception {
        bringeee.setToken("customer");
        bringeee.getOrderDetail(id, bringeee.getToken());
    }

    @When("{string} send POST request to confirm order with id {int}")
    public void sendPOSTRequestToConfirmOrderWithId(String role, int id) throws Exception {
        bringeee.setToken(role);
        bringeee.getOrderDetail(id, bringeee.getToken());
    }
}
