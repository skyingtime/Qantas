package com.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yangliu on 11/03/2017.
 */
public class Airport {
    private String code;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("international_airport")
    private boolean internationalAirport;

    @JsonProperty("regional_airport")
    private boolean regionalAirport;

    private Location location;

    @JsonProperty("currency_code")
    private String currencyCode;

    private String timezone;

    private Country country;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isInternationalAirport() {
        return internationalAirport;
    }

    public void setInternationalAirport(boolean internationalAirport) {
        this.internationalAirport = internationalAirport;
    }

    public boolean isRegionalAirport() {
        return regionalAirport;
    }

    public void setRegionalAirport(boolean regionalAirport) {
        this.regionalAirport = regionalAirport;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
