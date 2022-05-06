package stepdefinitions;

import io.cucumber.java.en.When;
import services.bringeeeAPI;

public class AdmOrderDetailStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();

    @When("admin send GET request order detail for id {int}")
    public void adminSendGETRequestOrderDetailForId(int id) throws Exception {
        bringeee.setToken("admin");
        bringeee.adminOrderDetail(id, bringeee.getToken());
    }

    @When("admin send GET request order detail for id {string}")
    public void adminSendGETRequestOrderDetailForId(String id) throws Exception {
        bringeee.setToken("admin");
        bringeee.adminOrderDetail(id, bringeee.getToken());
    }
    @When("{string} send GET order detail")
    public void sendGETOrderDetail(String role) throws Exception {
        bringeee.setToken(role);
        bringeee.adminOrderDetail(8, bringeee.getToken());
    }
}
