package resource;

public enum ApiResource {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	UpdatePlaceAPI("/maps/api/place/update/json");
	private String resource;
	ApiResource(String resource){
		this.resource=resource;
	}

	public String getResource() {
		return resource;
	}

}
