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

import android.support.annotation.NonNull;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanmay on 7/25/2016.
 */
public class GooglePlaceAutocompleteApi {

    public static final int TYPE_FILTER_ADDRESS = 2;
    public static final int TYPE_FILTER_CITIES = 5;
    public static final int TYPE_FILTER_ESTABLISHMENT = 34;
    public static final int TYPE_FILTER_GEOCODE = 1007;
    public static final int TYPE_FILTER_NONE = 0;
    public static final int TYPE_FILTER_REGIONS = 4;

    public static final LatLngBounds BOUNDS_WORLD =
            new LatLngBounds(new LatLng(-85, -180), new LatLng(85, 180));

    private static GooglePlaceAutocompleteApi mInstance;

    private GoogleApiClient mGoogleApiClient;
    private AutocompleteFilter cityFilter;
    private List<AutocompletePrediction> predictions;

    private GooglePlaceAutocompleteApi(GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
        cityFilter = new AutocompleteFilter.Builder().setTypeFilter(TYPE_FILTER_CITIES).build();
        predictions = new ArrayList<>();
    }

    public static void create(GoogleApiClient googleApiClient) {
        mInstance = new GooglePlaceAutocompleteApi(googleApiClient);
    }

    public static GooglePlaceAutocompleteApi getInstance() {
        if (mInstance != null) {
            return mInstance;
        } else {
            throw new RuntimeException("Api was not instantiated");
        }
    }

    public void release() {
        mInstance = null;
    }

    public void getCityPredictions(String param, final AutocompleteResultCallback callback) {
        PendingResult<AutocompletePredictionBuffer> result =
                Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient, param, BOUNDS_WORLD, cityFilter);
        result.setResultCallback(new ResultCallbacks<AutocompletePredictionBuffer>() {
            @Override
            public void onSuccess(@NonNull AutocompletePredictionBuffer autocompletePredictions) {
                predictions.clear();
                for (AutocompletePrediction prediction : autocompletePredictions) {
                    predictions.add(prediction);
                }
                autocompletePredictions.release();
                callback.onResult(predictions);
            }

            @Override
            public void onFailure(@NonNull Status status) {
                callback.onFailure(status);
            }
        });
    }

    public void getAutocompletePredictions(String param, LatLngBounds bounds, int filter, final AutocompleteResultCallback callback) {
        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder().setTypeFilter(filter).build();
        PendingResult<AutocompletePredictionBuffer> result =
                Places.GeoDataApi.getAutocompletePredictions(mGoogleApiClient, param, bounds, autocompleteFilter);
        result.setResultCallback(new ResultCallbacks<AutocompletePredictionBuffer>() {
            @Override
            public void onSuccess(@NonNull AutocompletePredictionBuffer autocompletePredictions) {
                predictions.clear();
                for (AutocompletePrediction prediction : autocompletePredictions) {
                    predictions.add(prediction);
                }
                autocompletePredictions.release();
                callback.onResult(predictions);
            }

            @Override
            public void onFailure(@NonNull Status status) {
                callback.onFailure(status);
            }
        });
    }

    public interface AutocompleteResultCallback {
        void onResult(List<AutocompletePrediction> predictions);

        void onFailure(Status status);
    }

}
