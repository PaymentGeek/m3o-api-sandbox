package starter.user;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class m3oUserAPIHelper {

    private static String BASE_URL;
    private static String RESOURCES;
    private static String KEYWORD;
    private static String BEARER;
    private static String PRODUCTS = "http://localhost:3030/products";
    private static String PRODUCT_BY_ID = "http://localhost:3030/products/{id}";
    private static String RESOURCES1 = "src/test/resources/attachments";

    public m3oUserAPIHelper(){
        try (InputStream input = new FileInputStream("config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            BASE_URL = prop.getProperty("BASE_URL");
            RESOURCES = prop.getProperty("RESOURCES");
            KEYWORD = prop.getProperty("KEYWORD");
            BEARER = prop.getProperty("BEARER");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void userServiceRequest(String crud, String endpoint, String userNo, String template) {
        String payload = generateStringFromResource(template);
        String updatedPayload = payload.replaceAll(KEYWORD, userNo);

        if (crud.equals("POST")) {
            if (!template.equals(null)){
                System.out.println("PAYLOAD BODY \n" + updatedPayload);
                SerenityRest.
                    given().
                    headers(
                        "Authorization",
                        "Bearer " + BEARER,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON).
                    body(updatedPayload).
                    when().
                    post(BASE_URL + endpoint ).prettyPrint();
            } else {
                System.out.println("Error: Payload template parameter missing");
            }
        }
    }

    public static String generateStringFromResource(String template) {
    File file = new File(RESOURCES+"\\"+template+".json");
    String absolutePath = file.getAbsolutePath();
    String payload = "";

    try {
        payload = new String(Files.readAllBytes(Paths.get(absolutePath)));
    } catch (IOException e) {
        e.printStackTrace();
    }
    return payload;
    }

    public static String saveJsonValueToFile(String key, String jsonFieldContent, String template) {
        File file = new File(RESOURCES+"\\"+template+".json");
        File file_out = new File(RESOURCES+"\\"+template+"_session.json");
        String absolutePath = file.getAbsolutePath();
        String payload = "";
        String replacedPayload = "";

        try {
            payload = new String(Files.readAllBytes(Paths.get(absolutePath)));
            replacedPayload = payload.replaceAll(KEYWORD, jsonFieldContent);
            PrintWriter out = new PrintWriter(file_out);
            out.write(replacedPayload);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payload;
    }

}
