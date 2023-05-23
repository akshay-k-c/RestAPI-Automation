package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.addMap;

public class TestData {
	
	public addMap addPlaceData(int accuracy,String name, String phone) {
	addMap request=new addMap();
	request.setAccuracy(accuracy);
	request.setAddress("29, side layout, cohen 09");
	request.setLanguage("French-IN");
	Location l=new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	request.setLocation(l);
	request.setName(name);
	request.setPhone_number(phone);
	List<String> types=new ArrayList<>();
	types.add("shoe park");
	types.add("sgop");
	request.setTypes(types);
	request.setWebsite("http://google.com");
	
	return request;
	
	}
	
	public String deletePlace(String placeId) {
		return "{\n"
				+ "\n"
				+ "    \"place_id\":\""+placeId+"\"\n"
				+ "}\n"
				+ "";
	}

}
