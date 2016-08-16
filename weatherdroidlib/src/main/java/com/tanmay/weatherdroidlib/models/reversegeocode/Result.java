
package com.tanmay.weatherdroidlib.models.reversegeocode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @SerializedName("address_components")
    @Expose
    public List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
    @SerializedName("formatted_address")
    @Expose
    public String formattedAddress;
    @SerializedName("geometry")
    @Expose
    public Geometry geometry;
    @SerializedName("place_id")
    @Expose
    public String placeId;
    @SerializedName("types")
    @Expose
    public List<String> types = new ArrayList<String>();

}
