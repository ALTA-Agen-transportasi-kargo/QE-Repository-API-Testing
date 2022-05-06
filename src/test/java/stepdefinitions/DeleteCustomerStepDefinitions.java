package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class DeleteCustomerStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send DELETE request to delete customer account with id {int}")
    public void adminSendDELETERequestToDeleteCustomerAccountWithId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.deleteCustomerID(id, bringeee.getToken());
    }

    @When("admin send DELETE request to delete customer account with id {string}")
    public void adminSendDELETERequestToDeleteCustomerAccountWithIdId(String id) throws Exception {
        bringeee.setToken("admin");
        bringeee.deleteCustomerID(id, bringeee.getToken());
    }

    @When("admin send DELETE request to delete customer account but not login")
    public void adminSendDELETERequestToDeleteCustomerAccountButNotLogin() throws Exception {
        bringeee.setToken("noLogin");
        bringeee.deleteCustomerID(1, bringeee.getToken());
    }
}
