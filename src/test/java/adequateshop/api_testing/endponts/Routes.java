package adequateshop.api_testing.endponts;


//AuthAccount
//        http://restapi.adequateshop.com/api/AuthAccount/Registration
//        post /api/AuthAccount/Login
//        post /api/AuthAccount/Registration

//Customer
//        http://restapi.adequateshop.com/api/Customer
//        get /api/Customer
//        post /api/Customer
//        delete /api/Customer/{id}
//        get /api/Customer/{id}
//        put /api/Customer/{id}

public class Routes {
    //NO implementation only roots string
    public static String base_url = "http://restapi.adequateshop.com/api";
    //API Authentication
    public static String authApi = "/AuthAccount";
    public static String login = base_url + authApi + "/Login";
    public static String registration = base_url + authApi + "/Registration";
    //API Customers
    public static String get_customer = base_url + "/customer";
    public static String post_customer = base_url + "/customer";
    public static String get_customer_by_id = base_url + "/customer/{id}";
    public static String delete_customer_by_id = base_url + "/customer/{id}";
    public static String put_customer_by_id = base_url + "/customer/{id}";


}
