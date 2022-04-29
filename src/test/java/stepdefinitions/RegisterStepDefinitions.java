package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class RegisterStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("user send POST request to register as {string}")
    public void userSendPOSTRequestToRegisterAs(String role) throws Exception {
        if (role.equalsIgnoreCase("customer")) {
            bringeee.registerCustomer("normal");
        } else {
            bringeee.registerDriver("normal");
        }
    }

    @When("user send POST request to register as {string} with case: {string}")
    public void userSendPOSTRequestToRegisterAsWithCase(String role, String condition) throws Exception {

        if (role.equalsIgnoreCase("customer")) {
            bringeee.registerCustomer(condition);
        } else {
            bringeee.registerDriver(condition);
        }


    }
}
