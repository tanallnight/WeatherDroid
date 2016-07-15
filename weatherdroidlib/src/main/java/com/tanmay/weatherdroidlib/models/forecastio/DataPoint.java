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

/**
 * Created by Tanmay on 7/14/2016.
 */
public class DataPoint {

    public long time;
    public String summary;
    public String icon;
    public long sunriseTime;
    public long sunsetTime;
    public double moonPhase;
    public double nearestStromDistance;
    public double nearestStromBearing;
    public double precipIntensity;
    public double precipIntensityMax;
    public long precipIntensityMaxTime;
    public double precipProbability;
    public String precipType;
    public double precipAccumulation;
    public double temperature;
    public double temperatureMin;
    public long temperatureMinTime;
    public double temperatureMax;
    public long temperatureMaxTime;
    public double apparentTemperature;
    public double apparentTemperatureMin;
    public long apparentTemperatureMinTime;
    public double apparentTemperatureMax;
    public long apparentTemperatureMaxTime;
    public double dewPoint;
    public double windSpeed;
    public double windBearing;
    public double cloudCover;
    public double humidity;
    public double pressure;
    public double visibility;
    public double ozone;

}
