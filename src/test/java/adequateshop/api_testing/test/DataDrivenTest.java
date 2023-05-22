package adequateshop.api_testing.test;

import adequateshop.api_testing.endponts.CustomerEndPoint;
import adequateshop.api_testing.payloads.CustomerPOJO;
import adequateshop.api_testing.utilities.XLSData;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;

public class DataDrivenTest {

    @Test(testName = "getData from xlsx sheet convert to POJO for testing")
    public void dataDrivenPOJOTest() throws IOException {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        for (int i = 0; i < XLSData.convertExcelToPojo().size(); i++) {
            CustomerPOJO customerFromXls = XLSData.convertExcelToPojo().get(i);

            Response response = CustomerEndPoint.createCustomer(customerFromXls);
            Assert.assertEquals(response.statusCode(), 201);

        }
    }

    @Test(testName = "getData from xlsx sheet for testing", dataProvider = "customerData", dataProviderClass = XLSData.class)
    public void dataDrivenTest(String id, String name, String email, String location) {
        Faker faker = new Faker();
        CustomerPOJO customerPOJO = new CustomerPOJO();
        customerPOJO.setId(faker.idNumber().hashCode());
        customerPOJO.setName(name);
        customerPOJO.setEmail(faker.internet().emailAddress());
        customerPOJO.setLocation(location);

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        Response response = CustomerEndPoint.createCustomer(customerPOJO);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(testName = "delete user", priority = 1, enabled = false)
    public void deleteUser(ITestContext context) {
        Response response = CustomerEndPoint.deleteCustomerById(context.getAttribute("id").toString());
        Assert.assertEquals(response.statusCode(), 200);
    }
}
