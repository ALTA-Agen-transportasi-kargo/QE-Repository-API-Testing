package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class CustCreatePaymentStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();
    @When("customer send POST request to create payment for order id {int} And use {string}")
    public void customerSendPOSTRequestToCreatePaymentForOrderIdAndUse(int id, String bank) throws Exception {
        bringeee.setToken("customer");
        bringeee.customerCreatePayment(id, bank, bringeee.getToken());
    }

    @When("customer send POST request to create payment for order id {string} And use {string}")
    public void customerSendPOSTRequestToCreatePaymentForOrderIdAndUse(String id, String bank) throws Exception {
        bringeee.setToken("customer");
        bringeee.customerCreatePayment(id, bank, bringeee.getToken());
    }

    @When("{string} send POST request to create payment for order id {int} And use {string}")
    public void sendPOSTRequestToCreatePaymentForOrderIdAndUse(String role, int id, String bank) throws Exception {
        bringeee.setToken(role);
        bringeee.customerCreatePayment(id, bank, bringeee.getToken());
    }
}
