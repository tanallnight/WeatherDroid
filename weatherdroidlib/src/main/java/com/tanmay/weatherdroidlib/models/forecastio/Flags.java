/*
 * Copyright 2016 Tanmay Parikh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tanmay.weatherdroidlib.models.forecastio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tanmay on 7/18/2016.
 */
public class Flags {
    @SerializedName("darksky-unavailable")
    public String darksky_unavailable;
    @SerializedName("darksky-stations")
    public List<String> darksky_stations;
    @SerializedName("datapoint-stations")
    public List<String> datapoint_stations;
    @SerializedName("madis-stations")
    public List<String> madis_stations;
    @SerializedName("isd-stations")
    public List<String> isd_stations;
    @SerializedName("lamp-stations")
    public List<String> lamp_stations;
    @SerializedName("metar-stations")
    public List<String> metar_stations;
    @SerializedName("metno-license")
    public String metno_license;
    public List<String> sources;
    public String units;
}
