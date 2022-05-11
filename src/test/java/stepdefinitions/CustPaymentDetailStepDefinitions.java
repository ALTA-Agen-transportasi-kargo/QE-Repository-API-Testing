package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class CustPaymentDetailStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("customer send GET request to show customer payment detail for id {int}")
    public void customerSendGETRequestToShowCustomerPaymentDetailForId(int id) throws Exception {
        bringeee.setToken("customer");
        bringeee.customerPaymentDetail(id, bringeee.getToken());
        System.out.println(lastResponse().prettyPrint());
    }

    @When("customer send GET request to show customer payment detail for id {string}")
    public void customerSendGETRequestToShowCustomerPaymentDetailForId(String id) throws Exception {
        bringeee.setToken("customer");
        bringeee.customerPaymentDetail(id, bringeee.getToken());
        System.out.println(lastResponse().prettyPrint());
    }

    @When("customer send GET request to show customer payment detail")
    public void customerSendGETRequestToShowCustomerPaymentDetail(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.customerPaymentDetail(3, bringeee.getToken());
        System.out.println(lastResponse().prettyPrint());
    }

    @When("{string} send GET request show customer payment detail for id {int}")
    public void sendGETRequestShowCustomerPaymentDetailForId(String role, int id) throws Exception {
        bringeee.setToken(role);
        bringeee.customerPaymentDetail(id, bringeee.getToken());
        System.out.println(lastResponse().prettyPrint());
    }
}
