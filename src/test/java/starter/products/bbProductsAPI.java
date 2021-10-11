package starter.products;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class bbProductsAPI {

    private static String PRODUCTS = "http://localhost:3030/products";
    private static String PRODUCT_BY_ID = "http://localhost:3030/products/{id}";
    private static String RESOURCES = "src/test/resources/attachments";

    @Step("I get all products from the store")
    public static void getAllProducts() {
        SerenityRest.given()
                .get(PRODUCTS).prettyPrint();
    }

    @Step("I get a product by id {0}")
    public static void getProductById(String id) {
        SerenityRest.given()
                .pathParam("id", id)
                .get(PRODUCT_BY_ID).prettyPrint();
    }

    @Step("I POST a new product for id {0}")
    public static void postProductforId(String xrayId) {
        String payload = generateStringFromResource(xrayId);

        //System.out.println("PAYLOAD BODY \n"+payload);
        SerenityRest.
                given().
                    contentType("application/json").
                    body(payload).
                when().
                    post(PRODUCTS).prettyPrint();
    }

    @Step("I PUT a new product {0} for id {1}")
    public static void putProductforId(String id, String xrayId) {
        String payload = generateStringFromResource(xrayId);

        //System.out.println("PAYLOAD BODY \n"+payload);
        SerenityRest.
                given().
                pathParam("id", id).
                contentType("application/json").
                body(payload).
                when().
                put(PRODUCT_BY_ID).prettyPrint();
    }

    @Step("I delete a product by id")
    public static void deleteProductById() {
        SerenityRest.
                given()
                .get(PRODUCTS).prettyPrint();

        ResponseBody body = SerenityRest.lastResponse();
        JsonPath jp = new JsonPath(body.asString());
        String productId = jp.get("data.id[0]").toString();
        System.out.println("Deleting product with ID " + productId);
        SerenityRest.given().
                pathParam("id", productId).
                delete(PRODUCT_BY_ID).prettyPrint();
    }

    @Step("I delete a product by id {0}")
    public static void deleteProductById(String productId) {
        SerenityRest.given().
                pathParam("id", productId).
                delete(PRODUCT_BY_ID).prettyPrint();
    }

    public static String generateStringFromResource(String xrayId) {

    File file = new File(RESOURCES+"\\"+xrayId+".json");
    String absolutePath = file.getAbsolutePath();
    String payload = "";

    try {
        payload = new String(Files.readAllBytes(Paths.get(absolutePath)));
    } catch (IOException e) {
        e.printStackTrace();
    }
    return payload;
    }

}
