package myProject.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import myProject.Utility.pojo.SampleCreateUserPOJO;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static myProject.Utility.ConfigReader.getBaseUrl;

public class SampleCreateUserSD {
    RequestSpecification reqSpec;
    RequestSpecification reqSpecGivenCreate;
    Response response;
    ResponseSpecification respSpec;
    String respStr;
    RequestSpecification reqSpecGivenUpdate;
    @Given("Add User Payload for Create User is created")
    public void add_user_payload_for_is_created() throws IOException {

        SampleCreateUserPOJO ob = new SampleCreateUserPOJO();
        ob.setName("Rajesh");
        ob.setJob("leader");

        reqSpec = new RequestSpecBuilder()
                .setBaseUri(getBaseUrl())
                .addHeader("Content-Type", "application/json")
                .build();

       /*  reqSpecGivenCreate = given().log().all().spec(reqSpec)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}");*/

        reqSpecGivenCreate = given().log().all().spec(reqSpec)
                .body(ob);


    }
    @When("user calls AddUser request with POST http request")
    public void user_calls_add_user_request_with_post_http_request() {

       response = reqSpecGivenCreate.when().post("/api/users");


    }

    @Then("^the API call got success with status code \"([^\"]*)\"$")
    public void the_api_call_got_success_with_status_code_something(String statusCode)
    {
        respSpec = new ResponseSpecBuilder()
                .expectStatusCode(Integer.parseInt(statusCode)).build();

         respStr = response.then().spec(respSpec).extract().asString();
        System.out.println(respStr);
    }


    @Then("job should be trainer;")
    public void job_should_be_trainer() {

        JsonPath jsonPath = new JsonPath(respStr);

        String expected = "leader";
        String actual =  jsonPath.getString("job");

        Assert.assertEquals(expected,actual);
    }

    @Then("^\"([^\"]*)\" should be \"([^\"]*)\";$")
    public void something_should_be_something(String key, String expectedValue)
             {

                 JsonPath jsonPath = new JsonPath(respStr);

                 //String expected = "leader";
                 String actual =  jsonPath.getString(key);

                 Assert.assertEquals(expectedValue,actual);

    }


    @Given("^Update User Payload for Update User is created$")
    public void update_user_payload_for_update_user_is_created() throws IOException {
                 SampleCreateUserPOJO ob = new SampleCreateUserPOJO();
                 ob.setName("Rajesh");
                 ob.setJob("leader");

                 reqSpec = new RequestSpecBuilder()
                         .setBaseUri(getBaseUrl())
                         .addHeader("Content-Type", "application/json")
                         .build();


        reqSpecGivenUpdate = given().log().all().spec(reqSpec)
                .body(ob);

    }

    @When("^user calls UpdateUser request with PUT http request with path parameter \"([^\"]*)\"$")
    public void user_calls_updateuser_request_with_put_http_request_with_path_parameter_something(String userId)
             {

               response = reqSpecGivenUpdate.when().put("/api/users/"+userId);

    }
}
