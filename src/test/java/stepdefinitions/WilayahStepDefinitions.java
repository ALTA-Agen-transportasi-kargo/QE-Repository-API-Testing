package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import services.bringeeeAPI;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class WilayahStepDefinitions {
    bringeeeAPI bringeee = new bringeeeAPI();
    String URL;

    @When("user send GET request to show all provinces")
    public void userSendGETRequestToShoAllProvinces() {
        bringeee.getAllProvinces();
    }

    @And("{string} should contain {string}")
    public void shouldContainProvName(String key, String value) {
        restAssuredThat(response -> response.body(key, equalToIgnoringCase(value)));
    }

    @When("user send GET request to show all cities for province id: {int}")
    public void userSendGETRequestToShowAllCitiesForProvinceId(int provId) {
        bringeee.getCitiesByProvinceId(provId);
    }

    @When("user send GET request to show all cities for province id: {string}")
    public void userSendGETRequestToShowAllCitiesForProvinceId(String invalidId) {
        bringeee.getCitiesByProvinceId(invalidId);
    }

    @Given("user has set province to DKI JAKARTA - id 11")
    public void userHasSetProvinceToDKIJAKARTAId() {
        URL = bringeee.setProvinceId(11);
    }

    @When("user send GET request to show all districts for city id: {int}")
    public void userSendGETRequestToShowAllDistrictsForCityId(int id) {
        bringeee.getDistrictsByCityId(id, URL);
    }

    @When("user send GET request to show all districts for city id: {string}")
    public void userSendGETRequestToShowAllDistrictsForCityId(String id) {
        bringeee.getDistrictsByCityId(id, URL);
    }
}
