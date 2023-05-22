package adequateshop.api_testing.endponts;

import adequateshop.api_testing.payloads.CustomerPOJO;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CustomerEndPoint {

    public static Response createCustomer(CustomerPOJO payload) {
        return given().contentType(ContentType.JSON).body(payload).when().post(Routes.post_customer);
    }

    public static Response readCustomerPage() {
        return given().contentType(ContentType.JSON).when().get(Routes.get_customer);
    }

    public static Response getCustomerById(String id) {
        return given().contentType(ContentType.JSON)
                      .pathParam("id", id).when().get(Routes.get_customer_by_id);
    }

    public static Response deleteCustomerById(String id) {
        return given().config(RestAssuredConfig.config().connectionConfig(ConnectionConfig.connectionConfig())).contentType(
                              ContentType.JSON).pathParam("id", id)
                      .when().delete(Routes.delete_customer_by_id);
    }

    public static Response putCustomerById(String id, CustomerPOJO customerPOJO) {
        return given().contentType(ContentType.JSON).pathParam("id", id).body(customerPOJO)
                      .when().post(Routes.put_customer_by_id);
    }
}
