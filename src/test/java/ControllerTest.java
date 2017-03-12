import com.controller.Controller;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONParser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by yangliu on 12/03/2017.
 */
public class ControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(new Controller()).build();
    }

    @Test
    public void getAllAirportsTest() throws Exception {
        mockMvc.perform(get("/api/airports"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                   JSONArray jsonArray = (JSONArray) JSONParser.parseJSON(mvcResult.getResponse().getContentAsString());
                    Assert.assertEquals("Total Airport number is 7080", 7080, jsonArray.length());
                });
    }

    @Test
    public void getAustralianAirportsTest() throws Exception {
        mockMvc.perform(get("/api/airports?countryCode=AU"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    JSONArray jsonArray = (JSONArray) JSONParser.parseJSON(mvcResult.getResponse().getContentAsString());
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject airport = (JSONObject) jsonArray.get(i);
                        JSONObject country = (JSONObject)airport.get("country");
                        Assert.assertEquals("All airports are in Australia", "AU", country.get("code"));
                    }
                });
    }

    @Test
    public void getAustralianInternationalAirportsTest() throws Exception {
        mockMvc.perform(get("/api/airports?countryCode=AU&internationalAirport=true"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    JSONArray jsonArray = (JSONArray) JSONParser.parseJSON(mvcResult.getResponse().getContentAsString());
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject airport = (JSONObject) jsonArray.get(i);
                        JSONObject country = (JSONObject)airport.get("country");
                        Assert.assertEquals("AU", country.get("code"));
                        Assert.assertEquals(true, airport.get("international_airport"));
                    }
                });
    }

    @Test
    public void getAustralianInternationalAndDomesticAirportsTest() throws Exception {
        mockMvc.perform(get("/api/airports?countryCode=AU&internationalAirport=true&domesticAirport=true"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    JSONArray jsonArray = (JSONArray) JSONParser.parseJSON(mvcResult.getResponse().getContentAsString());
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject airport = (JSONObject) jsonArray.get(i);
                        JSONObject country = (JSONObject)airport.get("country");
                        Assert.assertEquals("AU", country.get("code"));
                        Assert.assertEquals(true, airport.get("international_airport"));
                        Assert.assertEquals(true, airport.get("regional_airport"));
                    }
                });
    }

    @Test
    public void getAirportByCodeTest() throws Exception {
        mockMvc.perform(get("/api/airports?airportCode=BIP"))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    JSONArray jsonArray = (JSONArray) JSONParser.parseJSON(mvcResult.getResponse().getContentAsString());
                    Assert.assertEquals(1, jsonArray.length());
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject airport = (JSONObject) jsonArray.get(i);
                        JSONObject country = (JSONObject)airport.get("country");
                        Assert.assertEquals("AU", country.get("code"));
                        Assert.assertEquals("Bulimba", airport.get("display_name"));
                    }
                });
    }
}
