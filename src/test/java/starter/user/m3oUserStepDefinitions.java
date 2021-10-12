package starter.user;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class m3oUserStepDefinitions {

    @Steps
    m3oUserAPIHelper userAPI;

    @When("I call method {} from endpoint {} for user {} with body from template {}")
    public void userServiceRequest(String crud, String endpoint, String userNo, String template) {
        userAPI.userServiceRequest(crud, endpoint, userNo, template);
    }

    @Then("the response code should be {}")
    public void theResultIsValid(int responseCode) {
        restAssuredThat(response -> response.statusCode(responseCode));
    }

    @Then("the value in field {} equals {}")
    public void theFieldHasValue(String jsonField, String value) {
        ResponseBody response = SerenityRest.lastResponse();
        JsonPath jp = new JsonPath(response.asString());
        String jsonFieldContent = jp.get(jsonField).toString();
        Assert.assertEquals(jsonFieldContent,value);
    }

    @Then("the value in field {} is not null")
    public void fieldValueNotNull(String jsonField) {
        ResponseBody response = SerenityRest.lastResponse();
        JsonPath jp = new JsonPath(response.asString());
        String jsonFieldContent = jp.get(jsonField).toString();
        Assert.assertNotEquals(jsonFieldContent,null);
    }

    @Then("the value in field {} is saved to {}")
    public void saveJsonValueToFile(String key, String template) {
        ResponseBody response = SerenityRest.lastResponse();
        JsonPath jp = new JsonPath(response.asString());
        String jsonFieldContent = jp.get(key).toString();
        //System.out.println(jsonFieldContent);
        userAPI.saveJsonValueToFile(key, jsonFieldContent, template);

    }

    @Then("the response body field {} is not empty")
    public void theResultIsNotEmpty(String field) {
        ResponseBody body = SerenityRest.lastResponse();
        JsonPath json = body.jsonPath();

        Integer jsonField = json.get(field);
        Assert.assertTrue(jsonField != null );
    }

}
