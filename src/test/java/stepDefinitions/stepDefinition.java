package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resource.ApiResource;
import resource.TestDataBuild;
import resource.Utils;

public class stepDefinition extends Utils{
	
	
	RequestSpecification req;
	Response respPost;
	TestDataBuild testData = new TestDataBuild();
	JsonPath js;
	static String place_id;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
					 				
		req= given().log().all()
		.spec(requestSpecification())
		.body(testData.addPlacePayload(name, address, language));
		
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		ApiResource addResource = ApiResource.valueOf(resource);
		if(method.equalsIgnoreCase("POST")) {
			respPost = req.when().post(addResource.getResource())
				.then().log().all()
				.spec(responseSpecification())	
				.extract().response();	
		}
		else if(method.equalsIgnoreCase("GET")) {
			respPost = req.when().get(addResource.getResource())
				.then().log().all()
				.spec(responseSpecification())	
				.extract().response();	
			}		
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(int int1) {
		
		assertEquals(respPost.statusCode(),int1);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		
		assertEquals(getJsonPath(respPost,keyValue), expectedValue);
	}
	
	@Then("verify place_id created for {string} using {string}")
	public void verify_place_id_created_for_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(respPost,"place_id");	
		req= given().log().all()
			.spec(requestSpecification())
			.queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String name = getJsonPath(respPost,"name");
		Assert.assertEquals(expectedName, name);
				
	}
	
	@Given("Add Delete place payload")
	public void add_delete_place_payload() throws IOException {
	    
		req= given().log().all()
			.spec(requestSpecification())
			.body(testData.deletePlacePayload(place_id));
	}

}
