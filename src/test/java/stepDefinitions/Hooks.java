package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;
import io.restassured.response.Response;


public class Hooks  {
	
	@Before("@deletePlace")
	
	
	
	public void beforeDelete() throws IOException {
		
		stepDefinition meth=new stepDefinition();
		meth.add_place_payload_with_values(20, "rajesh", "(+91) 973 893 3937");
		meth.user_calls_with_request_by_passing("POST", "addPlaceAPI");
		meth.verify_the_name_from_get_response_as_using("rajesh","getPlaceAPI" );
		
		
	}
	
	
}
