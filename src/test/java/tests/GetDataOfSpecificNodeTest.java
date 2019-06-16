package tests;

import static com.jayway.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import utils.BaseClass;
import utils.PathURL;

public class GetDataOfSpecificNodeTest extends BaseClass{
	
	private Response res = null;
    
    @DataProvider
	private  static Object[][] latAndLongStateCode(){
    	return new Object[][] {
    		{40.730610,-73.935242,"NY"}
    	};
	 }
    
    @DataProvider
	private  static Object[][] postalCode(){
    	return new Object[][] {
    		{2066}
    	};
	 }
	
	@Test(dataProvider = "latAndLongStateCode")
	public void getCurrentWeatherUsingLatLong(double lat,double lon,String expectedStateCode) {
		res = given().
				param("lat",lat).
				param("lon",lon).
				param("key",BaseClass.key).
				when().
				get(PathURL.pathOfCurrentWeatherUsingLatAndLong());
		
		String actualStateCode = String.valueOf(res.
				then().
				contentType(ContentType.JSON).
				extract().
				path("data[0].state_code"));
		
		System.out.println(actualStateCode);
		Assert.assertEquals(actualStateCode,expectedStateCode);
	}
	
	@Test(dataProvider = "postalCode")
	public void getForecastWeatherUsingPostalCode(int postalCode) {
		res = given().
				param("postal_code",postalCode).
				param("key",BaseClass.key).
				when().
				get(PathURL.pathOfWeatherForecastUsingPostalCode()).
				then().
				contentType(ContentType.JSON).
				extract().response();
		
		List weather = res.
					then().
					contentType(ContentType.JSON).
					extract().
					path("data.weather");
		
		List timestamp = res.
				then().
				contentType(ContentType.JSON).
				extract().
				path("data.weather");
		
		for(Object item : weather){
			System.out.println(item);
		}
		
		for(Object item : timestamp){
			System.out.println(item);
		}
	}
	
	 @AfterTest
	    public static void resetBasePath(){
	        RestAssured.basePath = null;
	    }
}
