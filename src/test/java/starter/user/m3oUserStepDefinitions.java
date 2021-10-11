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
    m3oUserAPI userAPI;

    @When("I call method {} from endpoint {} for user {} with body from template {}")
    public void createUser(String crud, String endpoint, String userNo, String template) {
        userAPI.createUser(crud, endpoint, userNo, template);
    }

    @When("I get all products from the store")
    public void getAllProducts() {
        m3oUserAPI.getAllProducts();
    }

    @When("I get a product by id {}")
    public void getProductById(String id) {
        m3oUserAPI.getProductById(id);
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

    @Then("the response body field {} is not empty")
    public void theResultIsNotEmpty(String field) {
        ResponseBody body = SerenityRest.lastResponse();
        JsonPath json = body.jsonPath();

        Integer jsonField = json.get(field);
        Assert.assertTrue(jsonField != null );
    }
    @When("I POST a new product for id {}")
    public void postProduct(String xrayId) {
        m3oUserAPI.postProductforId(xrayId);
    }

    @When("I PUT a new product {} for id {}")
    public void putProduct(String id, String xrayId) {
        m3oUserAPI.putProductforId(id, xrayId);
    }

    @When("I delete a product by id")
    public void deleteProductById() {
        m3oUserAPI.deleteProductById();
    }

    @When("I delete a product by id {}")
    public void deleteProductById(String id) {
        m3oUserAPI.deleteProductById(id);
    }
}
