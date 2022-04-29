package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class CreateOrderStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("customer send POST request to create an order")
    public void customerSendPOSTRequestToCreateAnOrder() throws Exception {
        bringeee.setToken("customer");
        bringeee.createOrder("normal", bringeee.getToken());
    }

    @When("customer send POST request to create an order with condition: {string}")
    public void customerSendPOSTRequestToCreateAnOrderWithCondition(String condition) throws Exception {
        bringeee.setToken("customer");
        bringeee.createOrder(condition, bringeee.getToken());
    }

    @When("customer send POST request to create an order but not log in")
    public void customerSendPOSTRequestToCreateAnOrderButNotLogIn() throws Exception {
        bringeee.setToken("noLogin");
        bringeee.createOrder("normal", bringeee.getToken());
    }
}
