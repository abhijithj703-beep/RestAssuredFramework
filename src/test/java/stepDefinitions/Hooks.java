package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void BeforeScenario() throws IOException
	{
		//EXECUTE THIS CODE ONLY WHEN place_id is null
		
		//write a code that will give you place_id
		
		if(StepDefinition.place_id==null)
		{
		StepDefinition sd= new StepDefinition();
		
		sd.add_place_payload_with("Abhi","Hindi","Kochi");
		sd.user_calls_with_http_request("AddPlaceAPI","POST");
		sd.verify_place_id_created_maps_to_using_with_http_request("Abhi","GetPlaceAPI","GET");
		}
		
	}
}
