package com.controller;

import com.cache.AirportStore;
import com.model.Airport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yangliu on 11/03/2017
 */
@org.springframework.stereotype.Controller
@RequestMapping("/api")
public class Controller {

    @RequestMapping(value = "airports", method = RequestMethod.GET)
    public ResponseEntity<Airport[]> getAirport(@RequestParam(value = "countryCode", required = false) String countryCode,
                                                @RequestParam(value = "airportCode", required = false) String airportCode,
                                                @RequestParam(value = "internationalAirport", required = false) String isInternationalAirport,
                                                @RequestParam(value = "domesticAirport", required = false) String isDomesticAirport) {
        List<Airport> airportList = AirportStore.retrieveAirportList().stream()
                .filter(airport ->
                           (airportCode == null || airport.getCode().equalsIgnoreCase(airportCode))
                        && (countryCode == null || airport.getCountry().getCode().equalsIgnoreCase(countryCode))
                        && (isInternationalAirport == null || airport.isInternationalAirport() == Boolean.valueOf(isInternationalAirport))
                        && (isDomesticAirport == null || airport.isRegionalAirport() == Boolean.valueOf(isDomesticAirport))
                ).collect(Collectors.toList());
        return new ResponseEntity(airportList, HttpStatus.OK);
    }
}
