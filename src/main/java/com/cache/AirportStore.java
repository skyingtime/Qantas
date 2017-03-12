package com.cache;

import com.model.Airport;
import com.model.Airports;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by yangliu on 12/03/2017.
 */
public class AirportStore {
    private static List<Airport> airportList = null;
    private static final String ACCEPT_MEDIA_TYPE = "application/vnd.qantas.airports.v1+json;charset=utf-8";
    private static final String API_URL = "https://www.qantas.com.au/api/airports";

    private AirportStore(){}

    public static List<Airport> retrieveAirportList() {
        if(airportList == null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set("Accept", ACCEPT_MEDIA_TYPE);
            HttpEntity requestEntity = new HttpEntity(requestHeaders);
            ResponseEntity<Airports> airportsEntity = restTemplate.exchange(API_URL, HttpMethod.GET, requestEntity, Airports.class);
            Airports airports = airportsEntity.getBody();
            airportList = airports.getAirports();
        }
        return airportList;
    }
}
