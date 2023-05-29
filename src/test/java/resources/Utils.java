package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	 static RequestSpecification req;
	//static FileInputStream file;
	 //static Properties prop;

	public RequestSpecification requestspecification() throws IOException {
		
		if(req==null) {
		
		PrintStream log=new PrintStream(new FileOutputStream("log.txt"));
		 req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
				  .setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
				  .addFilter(RequestLoggingFilter.logRequestTo(log))
				  .addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		 return req;
		 
	}
		return req;
	}
	public static  String getGlobalValue(String key) throws IOException {
		return getPropertyValue(key);
		
	}
	
	public static String  getJsonPath(Response response,String key)
	{
		String resp=response.asString();
		JsonPath js1 =new JsonPath(resp);
	
		 return js1.get(key).toString();
		
		
	}
	public static String getPropertyValue(String key) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream file=new FileInputStream("src/test/java/resources/global.new.properties");
		prop.load(file);
		return prop.getProperty(key);
		
	}
	
	public static String getRequestPath() throws IOException {
	
		return getPropertyValue("path");
	}

}
