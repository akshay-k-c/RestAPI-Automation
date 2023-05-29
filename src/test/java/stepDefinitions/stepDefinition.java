package stepDefinitions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;

import pojo.Location;
import static org.junit.Assert.*;
import pojo.addMap;
import resources.APIResources;
import resources.ReUsableMethods;
import resources.TestData;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class stepDefinition extends Utils {
	
	 RequestSpecification req;
	 RequestSpecification res;
	 Response response;
	static  String placeId;

	 JsonPath js;
	 
	 	
	 TestData td=new TestData();
	

	 
	 @Given("add place payload with {int} {string} {string} values")
	 public void add_place_payload_with_values(Integer accuracy, String name, String phone) throws IOException {
	    
		  res= given().spec(requestspecification()).body(td.addPlaceData(accuracy, name,phone));
	 }
	
	 @Given("add place by passing {string} request")
	 public void add_place_by_passing_request(String filename) throws IOException {
	
		 
	String jsonBody=new String(Files.readAllBytes(Paths.get(getRequestPath()+filename)));
	
		 
	  res=given().spec(requestspecification()).body(jsonBody);
	  
	  
	 }

	  
	 @When("user calls with {string} request by passing {string}")
	 public void user_calls_with_request_by_passing(String method, String resource) {
		 
		 APIResources resoucres=APIResources.valueOf(resource);
		 
		 if(method.equalsIgnoreCase("POST")) {
		 
	       response=res.when().post(resoucres.getResource())
				.then().extract().response();
		 }
		 else if(method.equalsIgnoreCase("GET")){

		       response=res.when().get(resoucres.getResource())
					.then().extract().response();
		 }
		 else if(method.equalsIgnoreCase("DELETE")){

		       response=res.when().delete(resoucres.getResource())
					.then().extract().response();
		 }
	}

	@Then("validate the response code as {int}")
	public void validate_the_response_code_as(int code ) {
	   assertEquals(response.statusCode(),code);
	}

	@Then("check {string} in the resposne body is {string}")
	public void check_in_the_resposne_body_is(String keyValue , String expected) {
	   
	  assertEquals((getJsonPath(response,keyValue)),expected);
	}

	@Then("check {string} in the respose as {string}")
	public void check_in_the_respose_as(String keyValue, String expected) {
		assertEquals((getJsonPath(response,keyValue)),expected);
	}

	
	@Then("Verify the name from get response as {string} using {string}")
	public void verify_the_name_from_get_response_as_using(String name, String resource) throws IOException {
	  
		
		
		 placeId=getJsonPath(response,"place_id");
		  res= given().spec(requestspecification().queryParam("place_id", placeId));
		  
		  user_calls_with_request_by_passing("GET",resource);
		  
		  
		  String actualName=getJsonPath(response,"name");
		  assertEquals(actualName,name);
		
	}
	
	@Then("delete the place by passing {string} verify response as {int}")
	public void delete_the_place_by_passing_verify_response_as(String resource, int status) throws IOException {
		
		res=given().spec(requestspecification());
		
		user_calls_with_request_by_passing("DELETE",resource);
		
		 assertEquals(response.statusCode(),status);
	    
	}
	

  @Given("delete the place by passing payload")
public void delete_the_place_by_passing_payload() throws IOException{
    
	  
	  res=given().spec(requestspecification().body(td.deletePlace(placeId)));
	  
}





}
