package resources;

//enum is a special class in java which has collection of constant or methods

public enum APIResources {

	userLoginAPI("/api/userLogin"),
	userLogoutAPI("/api/systemLogOut"),
	getAllBatch("/api/getAllBatch");
	private String resource; 
	
	
	APIResources(String resourse)
	{
		this.resource=resourse;
	}
	
	public String getResource() {
		return resource;
	}
}
