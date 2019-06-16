package utils;

public class PathURL {
	
	public static String pathOfCurrentWeatherUsingLatAndLong(){
        String res = "/current";
        return res;
    }
	
	public static String pathOfWeatherForecastUsingPostalCode(){
        String res = "/forecast/hourly";
        return res;
    }
}