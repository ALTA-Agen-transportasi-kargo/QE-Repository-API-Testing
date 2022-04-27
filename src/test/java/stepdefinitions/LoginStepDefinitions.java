package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;
import services.bringeeeAPI;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class LoginStepDefinitions {
    bringeeeAPI bringee = new bringeeeAPI();

    @When("user send POST login request as {string}")
    public void userSendPOSTLoginRequestAs(String actor) throws Exception {
        bringee.authAs(actor);
    }

    @When("user send POST request with body: {string} from {string} payload")
    public void userSendPOSTRequestWithBodyFromPayload(String fileName, String folderName) {
        bringee.postLoginWithBody(fileName,folderName);
    }

    @Then("status response code should be {int}")
    public void statusResponseCodeShouldBe(int responseCode) {
        restAssuredThat(response -> response.statusCode(responseCode));

    }

    @And("return body is matched with {string} from {string} schema")
    public void returnBodyIsMatchedWith(String schema, String folderSchema) {
        String schemaPath = String.format("schema/%s/%s", folderSchema, schema);
        System.out.println(schemaPath);

        restAssuredThat(response -> response.assertThat().body(matchesJsonSchemaInClasspath(schemaPath)));
    }

    @Given("user has already had login token as {string}")
    public void userHasAlreadyHadLoginToken() {
    }

    @When("user send GET request to see the current user data")
    public void userSendGETRequestToSeeTheCurrentUserData() {
    }

    @Given("user hasn't already had login token")
    public void userHasnTAlreadyHadLoginToken() {
    }


    @And("user role should match with {string}")
    public void userRoleShouldMatchWith(String role) {
    }
}
