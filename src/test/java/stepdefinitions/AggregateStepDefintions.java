package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;

public class AggregateStepDefintions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send GET request to count orders")
    public void adminSendGETRequestToCountOrders() throws Exception {
        bringeee.setToken("admin");
        bringeee.orderCount(bringeee.getToken());
    }

    @When("admin send GET request to count orders by status {string}")
    public void adminSendGETRequestToCountOrdersByStatus(String filter) throws Exception {
        bringeee.setToken("admin");
        bringeee.orderCountStatus(filter, bringeee.getToken());
    }

    @When("admin send GET request to count orders by truck type id {int}")
    public void adminSendGETRequestToCountOrdersByTruckTypeId(int filter) throws Exception {
        bringeee.setToken("admin");
        bringeee.orderCountTruck(filter, bringeee.getToken());
    }

    @When("{string}send GET request to count orders")
    public void sendGETRequestToCountOrders(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.orderCount(bringeee.getToken());
    }

    @When("admin send GET request to count drivers")
    public void adminSendGETRequestToCountDrivers() throws Exception {
        bringeee.setToken("admin");
        bringeee.driverCount(bringeee.getToken());
    }

    @When("admin send GET request to count drivers by status {string}")
    public void adminSendGETRequestToCountDriversByStatus(String filter) throws Exception {
        bringeee.setToken("admin");
        bringeee.driverCountStatus(filter, bringeee.getToken());
    }

    @When("admin send GET request to count drivers by account status {string}")
    public void adminSendGETRequestToCountDriversByAccountStatus(String filter) throws Exception {
        bringeee.setToken("admin");
        bringeee.driverCountAccount(filter, bringeee.getToken());
    }

    @When("admin send GET request to count drivers by truck type id {int}")
    public void adminSendGETRequestToCountDriversByTruckTypeId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.driverCountTruck(id, bringeee.getToken());
    }

    @When("admin send GET request to count all customers")
    public void adminSendGETRequestToCountAllCustomers() throws Exception {
        bringeee.setToken("admin");
        bringeee.customerCount(bringeee.getToken());
    }

    @When("{string}send GET request to count customers")
    public void sendGETRequestToCountCustomers(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.customerCount(bringeee.getToken());
    }

    @When("{string}send GET request to count drivers")
    public void sendGETRequestToCountDrivers(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.driverCount(bringeee.getToken());
    }

    @When("admin send GET request to count truck types")
    public void adminSendGETRequestToCountTruckTypes() throws Exception {
        bringeee.setToken("admin");
        bringeee.truckTypeCount(bringeee.getToken());
    }

    @When("{string}send GET request to count truck types")
    public void sendGETRequestToCountTruckTypes(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.truckTypeCount(bringeee.getToken());
    }

    @When("admin send GET order statistics for {int} days prior")
    public void adminSendGETOrderStatisticsForDaysPrior(int day) throws Exception {
        bringeee.setToken("admin");
        bringeee.orderStatsPerDay(day, bringeee.getToken());
    }

    @And("report for today and {int} days prior will be shown")
    public void reportForTodayAndDaysPriorWillBeShown(int day) throws Exception {
        JSONObject responseJson = new JSONObject(lastResponse().asString());
        JSONArray data = responseJson.getJSONArray("data");

        JSONObject dataObject = data.getJSONObject(0);
        JSONArray label = dataObject.getJSONArray("label");

        int totalDay = day + 1;
        int totalData = label.length();

        Assert.assertEquals(totalDay, totalData);

    }

    @But("{string} should be empty")
    public void shouldBeEmpty(String key) {
        restAssuredThat(response -> response.assertThat().body(key, empty()));
    }

    @When("admin send GET order statistics for {string} days prior")
    public void adminSendGETOrderStatisticsForDaysPrior(String day) throws Exception {
        bringeee.setToken("admin");
        bringeee.orderStatsPerDay(day, bringeee.getToken());
    }

    @When("{string} send GET order statistics for {int} days prior")
    public void sendGETOrderStatisticsForDaysPrior(String role, int day) throws Exception {
        bringeee.setToken(role);
        bringeee.orderStatsPerDay(day, bringeee.getToken());
    }
}
