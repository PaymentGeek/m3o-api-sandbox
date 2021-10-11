package starter.products;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class bbProductsStepDefinitions {

    @Steps
    bbProductsAPI productsAPI;

    @When("I get all products from the store")
    public void getAllProducts() {
        bbProductsAPI.getAllProducts();
    }

    @When("I get a product by id {}")
    public void getProductById(String id) {
        bbProductsAPI.getProductById(id);
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
        bbProductsAPI.postProductforId(xrayId);
    }

    @When("I PUT a new product {} for id {}")
    public void putProduct(String id, String xrayId) {
        bbProductsAPI.putProductforId(id, xrayId);
    }

    @When("I delete a product by id")
    public void deleteProductById() {
        bbProductsAPI.deleteProductById();
    }

    @When("I delete a product by id {}")
    public void deleteProductById(String id) {
        bbProductsAPI.deleteProductById(id);
    }
}
