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

import com.tanmay.weatherdroidlib.models.forecastio.ForecastIORequest;
import com.tanmay.weatherdroidlib.models.forecastio.ForecastIOResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tanmay on 7/14/2016.
 */
public class ForecastIOApi {

    private static final String BASE_API_URL = "https://api.forecast.io/forecast/76ef6be969af62694b84fdd6679f7cb1/";

    private static ForecastIOApi mInstance = null;

    private Retrofit mRetrofit;
    private ForecastIOService forecastIOService;
    private String apiKey;

    private ForecastIOApi(String apiKey) {

        this.apiKey = apiKey;

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        forecastIOService = mRetrofit.create(ForecastIOService.class);
    }

    public static synchronized ForecastIOApi getInstance() {
        return mInstance;
    }

    public static void create(String apiKey) {
        mInstance = new ForecastIOApi(apiKey);
    }

    public static void close() {
        mInstance = null;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public void getWeather(ForecastIORequest request, final ForecastResponse responseCallback) {
        Call<ForecastIOResponse> call = forecastIOService.getWeather(apiKey, request, request.getRequestParams());
        call.enqueue(new Callback<ForecastIOResponse>() {
            @Override
            public void onResponse(Call<ForecastIOResponse> call, Response<ForecastIOResponse> response) {
                ForecastIOResponse forecastIOResponse = response.body();
                forecastIOResponse.headers.Cache_Control = response.headers().get("Cache-Control");
                forecastIOResponse.headers.expires = response.headers().get("Expires");
                forecastIOResponse.headers.X_Forecast_API_Calls = response.headers().get("X-Forecast-API-Calls");
                forecastIOResponse.headers.X_Response_Time = response.headers().get("X-Response-Time");
                responseCallback.onSuccess(forecastIOResponse);
            }

            @Override
            public void onFailure(Call<ForecastIOResponse> call, Throwable t) {
                responseCallback.onFail(t);
            }
        });
    }

    public interface ForecastResponse {
        void onSuccess(ForecastIOResponse response);

        void onFail(Throwable t);
    }

}
