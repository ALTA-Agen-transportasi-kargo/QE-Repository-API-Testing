package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class SetPriceStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send PATCH request to set price of customer order with id {int} to {int}")
    public void adminSendPATCHRequestToSetPriceOfCustomerOrderWithIdTo(int id, int price) throws Exception {
        bringeee.setToken("admin");
        bringeee.setPrice(id, price, bringeee.getToken());
    }

    @And("order fix price for order id {int} should be equal to {int}")
    public void orderFixPriceShouldBeEqualTo(int id, int price) throws Exception {
        bringeee.setToken("admin");
        bringeee.adminOrderDetail(id, bringeee.getToken());

        restAssuredThat(response -> response.assertThat().body("data.fix_price", equalTo(price)));
    }

    @And("order status is {string}")
    public void orderStatusIs(String status) {
        restAssuredThat(response -> response.assertThat().body("data.status", equalToIgnoringCase(status)));
    }

    @When("admin send PATCH request to set price of customer order with id {string} to {int}")
    public void adminSendPATCHRequestToSetPriceOfCustomerOrderWithIdTo(String id, int price) throws Exception {
        bringeee.setToken("admin");
        bringeee.setPrice(id, price, bringeee.getToken());
    }

    @When("{string} send PATCH request to set price of customer order with id {int} to {int}")
    public void sendPATCHRequestToSetPriceOfCustomerOrderWithIdTo(String role, int id, int price) throws Exception {
        bringeee.setToken(role);
        bringeee.setPrice(id, price, bringeee.getToken());
    }
}
