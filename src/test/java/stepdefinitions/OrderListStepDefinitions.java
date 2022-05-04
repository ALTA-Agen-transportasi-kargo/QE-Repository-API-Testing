package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.When;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class OrderListStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("customer send GET request to show order list with parameter: {string}")
    public void customerSendGETRequestToShowOrderListWithParameter(String parameter) throws Exception {
        bringeee.setToken("customer");
        bringeee.getOrderListBy(parameter, bringeee.getToken());
    }
    @When("customer send GET request to show order list with parameter: {int}")
    public void customerSendGETRequestToShowOrderListWithParameter(int parameter) throws Exception {
        bringeee.setToken("customer");
        bringeee.getOrderListBy(parameter, bringeee.getToken());
    }

    @And("order status should be matched with parameter: {string}")
    public void orderStatusShouldBeMatchedWithParameter(String parameter) {
        restAssuredThat(response -> response.body("data[0].status", equalTo(parameter)));
    }

    @When("user send GET request to show order list")
    public void customerSendGETRequestToShowOrderList() throws Exception {
        bringeee.setToken("noLogin");
        bringeee.getOrderListBy("",bringeee.getToken());
    }

    @But("data should be empty")
    public void dataShouldBeEmpty() {
        restAssuredThat(response -> response.body("data", empty()));
    }
}
