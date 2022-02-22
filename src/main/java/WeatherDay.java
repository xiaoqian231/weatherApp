/**
 * Object of this class have data about weather
 */
public class WeatherDay {
    private Integer weatherId;
    private String weather;
    private double temperature;
    private Double wind;
    private Integer clouds;
    private String icon;

    /**
     * constructor
     * @param weatherId
     * @param weather
     * @param temperature
     * @param wind
     * @param clouds
     * @param icon
     */
    public WeatherDay(Integer weatherId, String weather, double temperature, Double wind, Integer clouds, String icon) {
        this.weatherId = weatherId;
        this.weather = weather;
        this.temperature = temperature;
        this.wind = wind;
        this.clouds = clouds;
        this.icon = icon;
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public String getWeather() {
        return weather;
    }

    public double getTemperature() {
        return temperature;
    }

    public Double getWind() {
        return wind;
    }

    public Integer getClouds() {
        return clouds;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "WeatherDay{" +
                "weatherId=" + weatherId +
                ", weather='" + weather + '\'' +
                ", temperature=" + temperature +
                ", wind=" + wind +
                ", rain=" + clouds +
                '}';
    }
}
