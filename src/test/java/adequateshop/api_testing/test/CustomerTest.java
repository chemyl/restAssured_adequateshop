package adequateshop.api_testing.test;

import adequateshop.api_testing.endponts.CustomerEndPoint;
import adequateshop.api_testing.payloads.CustomerPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CustomerTest {

    CustomerPOJO customerPOJO;

    @BeforeTest
    public void setUpData() {
        Faker faker = new Faker();
        customerPOJO = new CustomerPOJO();
        customerPOJO.setName(faker.name().firstName());
//        customerPOJO.setId(faker.idNumber().hashCode());
        customerPOJO.setLocation(faker.address().city());
        customerPOJO.setEmail(faker.internet().emailAddress());
    }

    @Test(testName = "Create new Customer", groups = {})
    public void createNewCustomer(ITestContext context) {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        Response response = CustomerEndPoint.createCustomer(customerPOJO);
        context.setAttribute("id", response.body().jsonPath().getInt("id"));
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(testName = "Read Customer list")
    public void readCustomerPage() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        Response response = CustomerEndPoint.readCustomerPage();
        JSONArray arr = new JSONArray(response.asString());
        for (int i = 0; i < arr.length(); i++) {
            Assert.assertFalse(arr.getJSONObject(i).get("id").toString().isEmpty());
            Assert.assertFalse(arr.getJSONObject(i).get("name").toString().isEmpty());
            Assert.assertFalse(arr.getJSONObject(i).get("email").toString().isEmpty());
            Assert.assertFalse(arr.getJSONObject(i).get("location").toString().isEmpty());
        }
        response
                .then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/CustomerJSONSchema.json"));
    }

    @Test(testName = "Get customer by ID", dependsOnMethods = {"createNewCustomer"})
    public void getCustomerByID(ITestContext context) throws IOException {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        Response response = CustomerEndPoint.getCustomerById(context.getAttribute("id").toString());

        ObjectMapper objectMapper = new ObjectMapper();
        CustomerPOJO customerPOJO1 = objectMapper.readValue(response.getBody().asString(), CustomerPOJO.class);
        Assert.assertFalse(customerPOJO1.getName().isEmpty());
        Assert.assertFalse(customerPOJO1.getLocation().isEmpty());
        Assert.assertFalse(customerPOJO1.getEmail().isEmpty());
        System.out.println(customerPOJO1.getId() + "\n" + customerPOJO1.getEmail() + "\n" + customerPOJO1.getName());
    }

    @Test(testName = "Update customer Info", dependsOnMethods = {"createNewCustomer"})
    public void updateCustomerInfo(ITestContext context) {
        Faker faker = new Faker();
        CustomerPOJO oldCustomerData = customerPOJO;
        customerPOJO.setEmail(faker.internet().emailAddress());
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        Response updateResponse = CustomerEndPoint.putCustomerById(context.getAttribute("id").toString(), customerPOJO);
        Assert.assertEquals(updateResponse.statusCode(), 201);
        context.setAttribute("id", updateResponse.jsonPath().getInt("id"));

        Response getCustomer = CustomerEndPoint.getCustomerById(String.valueOf(updateResponse.jsonPath().getInt("id")));

        Assert.assertEquals(getCustomer.jsonPath().get("email"), customerPOJO.getEmail());
        Assert.assertEquals(oldCustomerData.getName(), customerPOJO.getName());
        Assert.assertEquals(oldCustomerData.getLocation(), customerPOJO.getLocation());
        Assert.assertEquals(oldCustomerData.getId(), customerPOJO.getId());
        Assert.assertEquals(updateResponse.jsonPath().getInt("id"), getCustomer.jsonPath().getInt("id"));

    }

    @Test(testName = "Delete customer by ID", dependsOnMethods = {"createNewCustomer"}, priority = 5, enabled = false)
    public void deleteCustomerById(ITestContext context) {
        RestAssured.filters(new ResponseLoggingFilter(), new RequestLoggingFilter());

        Response response = CustomerEndPoint.deleteCustomerById(context.getAttribute("id").toString());
        Assert.assertEquals(response.statusCode(), 200);

        Response getCustomer = CustomerEndPoint.getCustomerById(context.getAttribute("id").toString());
        Assert.assertEquals(getCustomer.statusCode(), 404);
    }
}
