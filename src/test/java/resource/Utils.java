package resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification reqspec;
	ResponseSpecification resspec;
	PrintStream log;
	public RequestSpecification requestSpecification() throws IOException {
		
		if(reqspec == null) {
			log = new PrintStream(new FileOutputStream("logging.txt"));
			reqspec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
		}	
		return reqspec;
	}
	
	public ResponseSpecification responseSpecification() {
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		return resspec;
	}
	
	public String getGlobalValue(String key) throws IOException {
		
		Properties p = new Properties();
		FileInputStream fs = new FileInputStream("D:\\restAssuredProject\\CucumberProject\\src\\test\\java\\resource\\global.properties");
		p.load(fs);
		return p.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.getString(key);
	}

}
