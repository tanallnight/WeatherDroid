
package com.tanmay.weatherdroidlib.models.reversegeocode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry {

    @SerializedName("bounds")
    @Expose
    public Bounds bounds;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("location_type")
    @Expose
    public String locationType;
    @SerializedName("viewport")
    @Expose
    public Viewport viewport;

}
