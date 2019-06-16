package utils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.jayway.restassured.RestAssured;

public class BaseClass {
	
	public static String key = "7a19b9872db740ddba60ee5151421d5a";
	public static String baseURI = "http://api.weatherbit.io/v2.0";
	
	@BeforeSuite
	public static void setBaseURI (){
        RestAssured.baseURI = baseURI;
    }
	
	@AfterSuite
	public static void  resetBaseURIAndPath() {
		RestAssured.baseURI = null;
		RestAssured.basePath = null;
	}
}
