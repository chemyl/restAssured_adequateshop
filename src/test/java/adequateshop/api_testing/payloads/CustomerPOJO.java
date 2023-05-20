package adequateshop.api_testing.payloads;

public class NewCustomerPOJO {

    String name;
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    String location;
//
//            "name": "string",
//            "email": "string",
//            "location": "string"
//    }
}
