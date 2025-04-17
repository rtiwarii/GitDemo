package resource;

import java.util.ArrayList;
import java.util.List;

import pojo.Locations;
import pojo.addPlaceGoogle;

public class TestDataBuild {
	
	public addPlaceGoogle addPlacePayload(String name, String address, String language) {
		
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		Locations ls = new Locations();
		ls.setLat(-38.383494);
		ls.setLng(33.427362);
		addPlaceGoogle ap = new addPlaceGoogle();
		ap.setAccuracy("25");
		ap.setName(name);
		ap.setPhone_number("(+91) 988 888 8888");
		ap.setAddress(address);
		ap.setWebsite("http://google.com");
		ap.setLanguage(language);
		ap.setTypes(types);
		ap.setLocation(ls);	
		
		return ap;
	}
	
	public String deletePlacePayload(String placeid) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeid+"\"\r\n"
				+ "}";
	}

}
