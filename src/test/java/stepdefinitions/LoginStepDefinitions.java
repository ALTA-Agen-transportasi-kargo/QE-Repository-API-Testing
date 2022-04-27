package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.bringeeeAPI;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginStepDefinitions {
    bringeeeAPI bringee = new bringeeeAPI();

    String tokenGenerated;

    @When("user send POST login request as {string}")
    public void userSendPOSTLoginRequestAs(String actor) throws Exception {
        bringee.authAs(actor);
    }

    @Then("status response code should be {int}")
    public void statusResponseCodeShouldBe(int responseCode) {
        restAssuredThat(response -> response.statusCode(responseCode));

    }

    @And("return body is matched with {string} from {string} schema")
    public void returnBodyIsMatchedWith(String schema, String folderSchema) {
        String schemaPath = String.format("schema/%s/%s", folderSchema, schema);

        restAssuredThat(response -> response.assertThat().body(matchesJsonSchemaInClasspath(schemaPath)));
    }

    @Given("user has already had login token as {string}")
    public void userHasAlreadyHadLoginToken(String role) throws Exception {
        this.tokenGenerated = bringee.getBearerToken(role);
    }

    @When("user send GET request to see the current user data")
    public void userSendGETRequestToSeeTheCurrentUserData() {
        bringee.authMe(tokenGenerated);
    }

    @Given("user hasn't already had login token")
    public void userHasnTAlreadyHadLoginToken() {
        this.tokenGenerated = "null";
    }


    @And("user role should match with {string}")
    public void userRoleShouldMatchWith(String role) {
        String key;

        if(role.equalsIgnoreCase("driver")) {
            key = "data.user.user.role";
        } else {
            key = "data.user.role";
        }

        restAssuredThat(response -> response.body(key, equalTo(role)));
    }
}
