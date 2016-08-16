
package com.tanmay.weatherdroidlib.models.reversegeocode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ReverseGeocodeResults {

    @SerializedName("results")
    @Expose
    public List<Result> results = new ArrayList<Result>();
    @SerializedName("status")
    @Expose
    public String status;

    public String getLocalityLong() {
        if (!results.isEmpty()) {
            for (Result result : results) {
                if (!result.addressComponents.isEmpty()) {
                    for (AddressComponent component : result.addressComponents) {
                        for (String type : component.types) {
                            if (type.equals("locality")) {
                                return component.longName;
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public String getLocalityShort() {
        if (!results.isEmpty()) {
            for (Result result : results) {
                if (!result.addressComponents.isEmpty()) {
                    for (AddressComponent component : result.addressComponents) {
                        for (String type : component.types) {
                            if (type.equals("locality")) {
                                return component.shortName;
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public String getAdminLvl1Long() {
        if (!results.isEmpty()) {
            for (Result result : results) {
                if (!result.addressComponents.isEmpty()) {
                    for (AddressComponent component : result.addressComponents) {
                        for (String type : component.types) {
                            if (type.equals("administrative_area_level_1")) {
                                return component.longName;
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public String getAdminLvl1Short() {
        if (!results.isEmpty()) {
            for (Result result : results) {
                if (!result.addressComponents.isEmpty()) {
                    for (AddressComponent component : result.addressComponents) {
                        for (String type : component.types) {
                            if (type.equals("administrative_area_level_1")) {
                                return component.shortName;
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public String getAdminLvl2Long() {
        if (!results.isEmpty()) {
            for (Result result : results) {
                if (!result.addressComponents.isEmpty()) {
                    for (AddressComponent component : result.addressComponents) {
                        for (String type : component.types) {
                            if (type.equals("administrative_area_level_2")) {
                                return component.longName;
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public String getAdminLvl2Short() {
        if (!results.isEmpty()) {
            for (Result result : results) {
                if (!result.addressComponents.isEmpty()) {
                    for (AddressComponent component : result.addressComponents) {
                        for (String type : component.types) {
                            if (type.equals("administrative_area_level_2")) {
                                return component.shortName;
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public String getCountryShort() {
        if (!results.isEmpty()) {
            for (Result result : results) {
                if (!result.addressComponents.isEmpty()) {
                    for (AddressComponent component : result.addressComponents) {
                        for (String type : component.types) {
                            if (type.equals("country")) {
                                return component.shortName;
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public String getCountryLong() {
        if (!results.isEmpty()) {
            for (Result result : results) {
                if (!result.addressComponents.isEmpty()) {
                    for (AddressComponent component : result.addressComponents) {
                        for (String type : component.types) {
                            if (type.equals("country")) {
                                return component.longName;
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

}
