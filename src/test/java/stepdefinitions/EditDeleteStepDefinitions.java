package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class EditDeleteStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("customer send DELETE request to delete account")
    public void customerSendDELETERequestToDeleteAccount() throws Exception {
        bringeee.setToken("customer");
        bringeee.deleteCustomer(bringeee.getToken());
    }

    @When("customer send DELETE request to delete account but not login")
    public void customerSendDELETERequestToDeleteAccountButNotLogin() throws Exception {
        bringeee.setToken("noLogin");
        bringeee.deleteCustomer(bringeee.getToken());
    }

    @When("customer send PUT request to edit profile")
    public void customerSendPUTRequestToEditProfile () throws Exception {
        bringeee.setToken("customer");
        bringeee.editProfile("normal", bringeee.getToken());
    }

    @When("customer send PUT request to edit profile with condition: {string}")
    public void customerSendPUTRequestToEditProfileWithCondition(String condition) throws Exception {
        bringeee.setToken("customer");
        bringeee.editProfile(condition, bringeee.getToken());
    }

    @When("customer send PUT request to edit profile but not log in")
    public void customerSendPUTRequestToEditProfileButNotLogIn() throws Exception {
        bringeee.setToken("noLogin");
        bringeee.editProfile("normal", bringeee.getToken());
    }

}

