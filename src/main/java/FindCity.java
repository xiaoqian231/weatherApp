import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FindCity {

    File json;

    /**
     * constructor, loads file to memory
     */
    public FindCity() {
        this.json = new File("city.list.json");

    }

    /**
     * finds city ID by name and country
     * @param name name of city
     * @param country country
     * @return ID of country
     */
    public Integer findCityId(String name, String country) {

        List<Map<String, Object>> foundCity = null;

        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        country = country.toUpperCase();


        try {
            foundCity = JsonPath.read(json, "$.[?(@.name == '" + name + "' && @.country == '" + country + "')]");
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (foundCity.size() == 0) {
            return null;
        }

        return (Integer) foundCity.get(0).get("id");

    }
}
