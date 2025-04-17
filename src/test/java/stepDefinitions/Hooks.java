package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		

		stepDefinition sd = new stepDefinition();
		if(stepDefinition.place_id==null) {
			sd.add_place_payload_with("Coimbatore", "1129, side layout, Coimbatore", "French");
			sd.user_calls_with_http_request("AddPlaceAPI", "POST");
			sd.verify_place_id_created_for_using("Coimbatore", "GetPlaceAPI");
		}
		
		
	}

}
