package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Utils {
	static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
//		RestAssured.baseURI = "https://schoolinst.purestudy.com";

		if (req == null) {
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
					.setContentType("application/json; charset=UTF-8").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return req;
		}
		return req;
	}

	public String getGlobalValue(String URL) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Vaibhav Gholap\\eclipse-workspace\\ERPSchoolAPIFinal\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(URL);

	}
	
	public String getJsonPath(Response response, String token)
	{
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(token).toString();
	}
}
