package step;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FeatureSteps {

    @Given("the API base URL is {string}")
    public void theAPIBaseURLIs(String arg0) {
    }

    @When("a GET request is made to {string}")
    public void aGETRequestIsMadeTo(String arg0) {
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int arg0) {
    }

    @And("the response should contain {string}")
    public void theResponseShouldContain(String arg0) {
    }

    @And("the response should have the following structure:")
    public void theResponseShouldHaveTheFollowingStructure() {
    }

    @Given("the request body:")
    public void theRequestBody() {
    }

    @When("a POST request is made to {string}")
    public void aPOSTRequestIsMadeTo(String arg0) {
    }

    @When("a PUT request is made to {string}")
    public void aPUTRequestIsMadeTo(String arg0) {
    }

    @When("a DELETE request is made to {string}")
    public void aDELETERequestIsMadeTo(String arg0) {
    }

    @And("the response should be empty")
    public void theResponseShouldBeEmpty() {
    }

    @Given("the request parameters:")
    public void theRequestParameters() {
    }

    @Given("a valid API key")
    public void aValidAPIKey() {
    }

    @Given("an invalid API key")
    public void anInvalidAPIKey() {
    }

    @And("the response should contain {string} with value {string}")
    public void theResponseShouldContainWithValue(String arg0, String arg1) {
    }
}
