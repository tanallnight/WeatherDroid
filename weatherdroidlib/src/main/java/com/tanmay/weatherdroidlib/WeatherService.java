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

package com.tanmay.weatherdroidlib;

import android.support.annotation.StringDef;

import com.tanmay.weatherdroidlib.apis.ForecastIOApi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tanmay on 7/14/2016.
 */
public class WeatherService {

    public static final String SERVICE_FORECAST_IO = "forecast.io";
    public static final String SERVICE_WEATHER_UNDERGROUND = "weatherunderground";
    private Map<String, String> services = new HashMap<>();

    private WeatherService(Map<String, String> services) {
        this.services = services;
        if (services.containsKey(SERVICE_FORECAST_IO)) {
            ForecastIOApi.create(services.get(SERVICE_FORECAST_IO));
        }
        if (services.containsKey(SERVICE_WEATHER_UNDERGROUND)) {

        }
    }

    @StringDef({SERVICE_WEATHER_UNDERGROUND, SERVICE_FORECAST_IO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Service {
    }

    public static class Builder {

        private Map<String, String> services = new HashMap<>();

        public Builder addService(@Service String service, String apiKey) {
            services.put(service, apiKey);
            return this;
        }

        public WeatherService build() {
            return new WeatherService(services);
        }

    }

}
