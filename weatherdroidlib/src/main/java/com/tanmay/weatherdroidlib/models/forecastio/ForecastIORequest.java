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

import android.support.annotation.StringDef;
import android.text.TextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tanmay on 7/14/2016.
 */
public class ForecastIORequest {

    public static final String UNITS_SI = "si";
    public static final String UNITS_US = "us";
    public static final String UNITS_CA = "ca";
    public static final String UNITS_UK = "uk2";
    public static final String UNITS_AUTO = "auto";
    public static final String BLOCK_CURRENTLY = "currently";
    public static final String BLOCK_MINUTELY = "minutely";
    public static final String BLOCK_HOURLY = "hourly";
    public static final String BLOCK_DAILY = "daily";
    public static final String BLOCK_ALERTS = "alerts";
    public static final String BLOCK_FLAGS = "flags";
    public static final String LANGUAGE_ARABIC = "ar";
    public static final String LANGUAGE_BELARUSIAN = "be";
    public static final String LANGUAGE_BOSNIAN = "bs";
    public static final String LANGUAGE_CZECH = "cs";
    public static final String LANGUAGE_GERMAN = "de";
    public static final String LANGUAGE_GREEK = "el";
    public static final String LANGUAGE_ENGLISH = "en";
    public static final String LANGUAGE_SPANISH = "es";
    public static final String LANGUAGE_FRENCH = "fr";
    public static final String LANGUAGE_CROATIAN = "hr";
    public static final String LANGUAGE_HUNGARIAN = "hu";
    public static final String LANGUAGE_INDONESIAN = "id";
    public static final String LANGUAGE_ITALIAN = "it";
    public static final String LANGUAGE_ICELANDIC = "is";
    public static final String LANGUAGE_CORNISH = "kw";
    public static final String LANGUAGE_NORWEGIAN_BOKMAL = "nb";
    public static final String LANGUAGE_DUTCH = "nl";
    public static final String LANGUAGE_POLISH = "pl";
    public static final String LANGUAGE_PORTUGUESE = "pt";
    public static final String LANGUAGE_RUSSIAN = "ru";
    public static final String LANGUAGE_SLOVAK = "sk";
    public static final String LANGUAGE_SERBIAN = "sr";
    public static final String LANGUAGE_SWEDISH = "sv";
    public static final String LANGUAGE_TETUM = "tet";
    public static final String LANGUAGE_TURKISH = "tr";
    public static final String LANGUAGE_UKRAINIAN = "uk";
    public static final String LANGUAGE_IGPAY_ATINLAY = "x-pig-latin";
    public static final String LANGUAGE_SIMPLIFIED_CHINESE = "zh";
    public static final String LANGUAGE_TRADITIONAL_CHINESE = "zh-tw";
    private static final String KEY_UNITS = "units";
    private static final String KEY_LANGUAGE = "lang";
    private static final String KEY_EXCLUDE = "exclude";
    private static final String KEY_EXTEND = "extend";
    private double latitude;
    private double longitude;
    private long time;
    private String language;
    @Blocks
    private String extend;
    @RequestUnits
    private String units;
    @Blocks
    private List<String> excludeBlocks = new ArrayList<>();

    public ForecastIORequest(double latitude, double longitude, long time,
                             String language, @RequestUnits String units,
                             @Blocks List<String> excludeBlocks, @Blocks String extend) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.language = language;
        this.units = units;
        this.excludeBlocks = excludeBlocks;
        this.extend = extend;
    }

    public boolean useTime() {
        return time != 0;
    }

    public Map<String, String> getRequestParams() {
        Map<String, String> query = new HashMap<>();
        query.put(KEY_UNITS, units);
        query.put(KEY_LANGUAGE, language);
        if (excludeBlocks.size() != 0) {
            query.put(KEY_EXCLUDE, getExcludeBlock());
        }
        if (getExtendBlock() != null) {
            query.put(KEY_EXTEND, getExtendBlock());
        }
        return query;
    }

    @Override
    public String toString() {
        String params = latitude + "," + longitude;
        return useTime() ? params + "," + time : params;
    }

    private String getExcludeBlock() {
        return excludeBlocks.size() > 0 ? TextUtils.join(",", excludeBlocks) : null;
    }

    private String getExtendBlock() {
        if (extend != null) {
            return extend.equals(BLOCK_HOURLY) ? BLOCK_HOURLY : null;
        } else {
            return null;
        }
    }

    @StringDef({UNITS_SI, UNITS_CA, UNITS_US, UNITS_UK, UNITS_AUTO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestUnits {
    }

    @StringDef({BLOCK_ALERTS, BLOCK_CURRENTLY, BLOCK_DAILY, BLOCK_FLAGS,
            BLOCK_HOURLY, BLOCK_MINUTELY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Blocks {
    }

    public static class Builder {

        private double latitude;
        private double longitude;
        private long time;
        private String language = LANGUAGE_ENGLISH;
        @RequestUnits
        private String units = UNITS_AUTO;
        @Blocks
        private String extend;
        @Blocks
        private List<String> excludeBlocks = new ArrayList<>();

        public Builder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongtiude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setTime(long time) {
            this.time = time;
            return this;
        }

        public Builder setUnits(@RequestUnits String units) {
            this.units = units;
            return this;
        }

        public Builder exclude(@Blocks String exclude) {
            this.excludeBlocks.add(exclude);
            return this;
        }

        public Builder setLanguage(String language) {
            this.language = language;
            return this;
        }

        public Builder extendHourly() {
            this.extend = BLOCK_HOURLY;
            return this;
        }

        public ForecastIORequest build() {
            return new ForecastIORequest(latitude, longitude, time, language, units, excludeBlocks, extend);
        }

    }

}
