package Pojo;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Activity3 {
    @JsonProperty("post code")
    private String postCode;
    private String country;
    @JsonProperty("country abbreviation")
    private String countryAbbreviation;
    private List<PlacesItem> places;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setPlaces(List<PlacesItem> places) {
        this.places = places;
    }

    public List<PlacesItem> getPlaces() {
        return places;
    }

    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
    }

    @Override
    public String toString() {
        return
                "Activity3{" +
                        "country = '" + country + '\'' +
                        ",places = '" + places + '\'' +
                        ",country abbreviation = '" + countryAbbreviation + '\'' +
                        ",post code = '" + postCode + '\'' +
                        "}";
    }
}