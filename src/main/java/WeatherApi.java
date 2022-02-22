import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * class used to connect and get data about weather
 */
public class WeatherApi {

    private String jsonText;

    private final String apiKey = "77f78082f798fd25c52ceeb43db100dd";

    /**
     * constructor
     * @param cityId references city id from openweather
     */
    public WeatherApi(int cityId) {
        try {
            getJson(cityId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads all JSON file and converts it's to string
     * @param rd reader revived by connection to API
     * @return JSON string file
     * @throws IOException read exception
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * if JsonPath returns int value somewhere where usually returns double, it converts int to Double
     * @param a Integer or int to convert
     * @return Double value of passed argument
     */
    private Double castToDouble(Object a) {
        String b = String.valueOf(a);
        Double c = Double.valueOf(b);
        return c;
    }

    /**
     * Method gets weather by id, hours (0:00,3:00,6:00,9:00,...)
     * @param index index of hour witch 3 hours we want to get weather
     * @return WeatherDay object
     * @throws PathNotFoundException JsonPath exception
     */
    private WeatherDay getWeatherDayById(int index)  {
        Integer weatherId = null;
        String weather = null;
        double temperature = 0;
        Double wind = null;
        Integer clouds = null;
        String icon = "";






        try {
            weatherId = JsonPath.read(jsonText, "$.list[" + index + "].weather[0].id");
        } catch (PathNotFoundException e) {}

        try {
            weather = JsonPath.read(jsonText, "$.list[" + index + "].weather[0].main");
        } catch (PathNotFoundException e) {
        } catch (ClassCastException e) {
        }

        try {
            temperature = JsonPath.read(jsonText, "$.list[" + index + "].main.temp");
        } catch (PathNotFoundException e) {
        } catch (ClassCastException e) {
            temperature = castToDouble(JsonPath.read(jsonText, "$.list[" + index + "].main.temp"));
        }

        try {
            wind = JsonPath.read(jsonText, "$.list[" + index + "].wind.speed");
        } catch (PathNotFoundException e) {
        } catch (ClassCastException e) {
            wind = castToDouble(JsonPath.read(jsonText, "$.list[" + index + "].wind.speed"));
        }

        try {
            clouds = JsonPath.read(jsonText, "$.list[" + index + "].clouds.all");
        } catch (PathNotFoundException e) {
        } catch (ClassCastException e) {
        }

        try {
            icon = JsonPath.read(jsonText, "$.list[" + index + "].weather[0].icon");
        } catch (PathNotFoundException e) {
        }

        return new WeatherDay(weatherId, weather, temperature, wind, clouds, icon);
    }

    /**
     * Connects to internet and gets weather by cityId
     * @param cityId Id of city
     * @throws IOException
     */
    private void getJson(int cityId) throws IOException {

        String apiRequest = "http://api.openweathermap.org/data/2.5/forecast?id=" + cityId + "&appid=" + apiKey + "&units=metric";


        InputStream is = new URL(apiRequest).openStream();


        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        jsonText = readAll(rd);

    }

    /**
     * Method returns city name from received data
     * @return city name
     */
    public String getCityFromJson(){
        String cityName = JsonPath.read(jsonText, "$.city.name");
        return cityName;
    }

    /**
     * returns weather for today, next 1, 2, 3 days
     * @return ArrayList of objects WeatherDay
     * @throws IOException
     */
    public ArrayList<WeatherDay> getWeatherList() throws IOException {
        ArrayList<WeatherDay> list = new ArrayList<>();

        list.add(getWeatherDayById(0));
        list.add(getWeatherDayById(8));
        list.add(getWeatherDayById(16));
        list.add(getWeatherDayById(24));

        return list;
    }

}
