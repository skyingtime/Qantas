package com.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yangliu on 11/03/2017.
 */
public class Country {

    private String code;

    @JsonProperty("display_name")
    private String displayName;


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
}
