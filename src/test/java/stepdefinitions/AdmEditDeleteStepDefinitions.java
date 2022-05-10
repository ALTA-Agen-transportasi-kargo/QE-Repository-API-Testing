package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class AdmEditDeleteStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

//  Edit Driver

    @When("admin send PUT request to edit profile driver for id {int}")
    public void adminSendPUTRequestToEditProfile(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.adminEditProfileDriver(id, bringeee.getToken());
    }


    @When("admin send PUT request to edit profile driver but not log in")
    public void adminSendPUTRequestToEditProfileDriverButNotLogIn() throws Exception {
        bringeee.setToken("noLogin");
        bringeee.adminEditProfileDriver(14, bringeee.getToken());
    }


//  Delete Driver

    @When("admin send DELETE request to delete driver account with id {int}")
    public void adminSendDELETERequestToDeleteDriverAccountWithId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.deleteDriverID(id, bringeee.getToken());
    }

    @When("admin send DELETE request to delete driver account with id {string}")
    public void adminSendDELETERequestToDeleteDriverAccountWithIdId(String id) throws Exception {
        bringeee.setToken("admin");
        bringeee.deleteDriverID(id, bringeee.getToken());
    }

    @When("admin send DELETE request to delete driver account but not login")
    public void adminSendDELETERequestToDeleteDriverAccountButNotLogin() throws Exception {
        bringeee.setToken("noLogin");
        bringeee.deleteDriverID(1, bringeee.getToken());
    }
}
