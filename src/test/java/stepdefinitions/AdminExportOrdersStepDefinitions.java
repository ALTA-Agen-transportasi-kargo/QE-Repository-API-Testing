package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.junit.Assert.*;

public class AdminExportOrdersStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send POST request export orders for month {int} and year {int}")
    public void adminSendPOSTRequestExportOrdersForMonthAndYear(int month, int year) throws Exception {
        bringeee.setToken("admin");
        bringeee.exportOrders(month, year, bringeee.getToken());
    }

    @And("response body length should be greater than {int} bytes")
    public void responseBodyLengthShouldBeGreaterThan(int minLength) {
        Response response = lastResponse();
        byte[] result = response.asByteArray();
        int length = result.length;

        assertTrue(length > minLength);
    }

    @But("response body length should be equal or lower than {int} bytes")
    public void responseBodyLengthShouldBeEqualOrLowerThanBytes(int minLength) {
        Response response = lastResponse();
        byte[] result = response.asByteArray();
        int length = result.length;

        assertTrue(length <= minLength);
    }

    @When("admin send POST request export orders for month {string} and year {string}")
    public void adminSendPOSTRequestExportOrdersForMonthAndYear(String month, String year) throws Exception {
        bringeee.setToken("admin");
        bringeee.exportOrders(month, year, bringeee.getToken());
    }

    @When("{string} send POST request export orders for month {int} and year {int}")
    public void sendPOSTRequestExportOrdersForMonthAndYear(String role, int month, int year) throws Exception {
        bringeee.setToken(role);
        bringeee.exportOrders(month, year, bringeee.getToken());
    }
}
