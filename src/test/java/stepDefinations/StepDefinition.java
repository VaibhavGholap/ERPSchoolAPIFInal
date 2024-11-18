package stepDefinations;

import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	ResponseSpecification res;
	RequestSpecification userAdminLoginResponce;
	Response response;
	TestDataBuild data = new TestDataBuild();

	@Given("user Login payload with {string} {string}")
	public void user_login_payload_with(String user_name, String password) throws IOException {

		userAdminLoginResponce = RestAssured.given().spec(requestSpecification())
				.body(data.adminUserLoginPayload(user_name, password));
	}

	@When("user call {string} API with {string} http request")
	public void user_call_api_with_http_request(String resource, String method) {

		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json; charset=UTF-8")
				.build();

		if (method.equalsIgnoreCase("POST"))
			response = userAdminLoginResponce.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = userAdminLoginResponce.when().get(resourceAPI.getResource());

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {

		assertEquals(response.statusCode(), 200);
	}

	@Then("{string} in responce is {string}")
	public void in_responce_is(String resultValue, String expectedValue) {

//		String resp = response.asString();
//		js = new JsonPath(resp);
		
		assertEquals(getJsonPath(response,resultValue).toString(), expectedValue);

	}

	@Then("verify token created maps to {string} using {string} API")
	public void verify_token_created_maps_to_using_api(String string, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions
	String	token=getJsonPath(response, "token");
		userAdminLoginResponce = RestAssured.given().spec(requestSpecification()).header("token",token);
		user_call_api_with_http_request(resource, "GET");
		String actualName=getJsonPath(response, "name");
		System.out.println(actualName);
	}
}
