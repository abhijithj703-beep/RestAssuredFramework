package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;



public class StepDefinition extends Utils{

	RequestSpecification request;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String 	place_id; //This is given as static since after each test , all the values will get cleared. By declaring it as static , the place_id value will get retained throughout the tests 

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		
		// Storing the entire request to a variable
		request = given().spec(requestSpecification())
				.body(data.addPlacePayLoad(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions
		
		//constructor will be called with the value of the resource which you pass
		APIResources resourceAPI=APIResources.valueOf(resource);
		
		
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
			response = request.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response=request.when().get(resourceAPI.getResource());
	}

	@Then("the API call got success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
		// Write code here that turns the phrase above into concrete actions
		
		String actualValue=getJsonPath(response, key);
		System.out.println(actualValue);
		
		assertEquals(actualValue,expectedValue);

     }
	
	@Then("verify place_Id created maps to {string} using {string} with {string} request")
	public void verify_place_id_created_maps_to_using_with_http_request(String expectedName, String resource, String method) throws IOException {
	   
		place_id=getJsonPath(response,"place_id");
		
		
		request=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, method);
		String actualName=getJsonPath(response,"name");
		
		assertEquals(actualName,expectedName);
		
		
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		request = given().spec(requestSpecification())
				.body(data.deletePlacePayload(place_id));
	}
}
