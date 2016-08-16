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

package com.tanmay.weatherdroidlib.api;

import com.google.gson.Gson;
import com.tanmay.weatherdroidlib.models.reversegeocode.ReverseGeocodeResults;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tanmay on 8/15/2016.
 */
public class GoogleReverseGeocodingApi {

    public interface ReverseGeocodeResultListener {
        void onSuccess(ReverseGeocodeResults results);

        void onFail(Exception e);
    }

    private static final String BASE_API_URL = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String RESULT_TYPE = "&result_type=locality";
    private static final String LAT_LNG = "?latlng=";
    private static final String KEY = "&key=";

    private String apiKey;
    private OkHttpClient client;
    private Gson gson;

    public GoogleReverseGeocodingApi(String apiKey) {
        this.apiKey = apiKey;
        client = new OkHttpClient();
        gson = new Gson();
    }

    private String getUrl(String latlng) {
        return BASE_API_URL + LAT_LNG + latlng + RESULT_TYPE + KEY + apiKey;
    }

    public void reverseGeocode(double latitude, double longitude, final ReverseGeocodeResultListener callbackListener) {
        String latlng = latitude + "," + longitude;
        String url = getUrl(latlng);
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callbackListener.onFail(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                ReverseGeocodeResults results = gson.fromJson(responseBody, ReverseGeocodeResults.class);
                callbackListener.onSuccess(results);
            }
        });
    }

}
