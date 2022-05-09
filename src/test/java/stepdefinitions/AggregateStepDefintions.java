package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

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
}
