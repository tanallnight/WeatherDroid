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

package com.tanmay.weatherdroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Places;
import com.tanmay.weatherdroidlib.api.GooglePlaceAutocompleteApi;
import com.tanmay.weatherdroidlib.models.forecastio.ForecastIORequest;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    TextView textView;
    EditText editTextView;
    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, this)
                .build();

        GooglePlaceAutocompleteApi.create(googleApiClient);

        textView = (TextView) findViewById(R.id.response);
        textView.setMovementMethod(new ScrollingMovementMethod());

        editTextView = (EditText) findViewById(R.id.search);
        editTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String param = editable.toString();
                if (param.length() != 0) {
                    GooglePlaceAutocompleteApi.getInstance().getCityPredictions(param, new GooglePlaceAutocompleteApi.AutocompleteResultCallback() {
                        @Override
                        public void onResult(List<AutocompletePrediction> predictions) {
                            for (AutocompletePrediction prediction : predictions) {
                                Log.d("PREDICTIONS", prediction.getFullText(null).toString());
                            }
                            Log.d("PREDICTIONS", "----------END---------");
                        }

                        @Override
                        public void onFailure(Status status) {

                        }
                    });
                }
            }
        });

        ForecastIORequest request = new ForecastIORequest.Builder()
                .setLatitude(37.8267)
                .setLongtiude(-122.423)
                .setUnits(ForecastIORequest.UNITS_LOCAL_CONVERT)
                .exclude(ForecastIORequest.BLOCK_MINUTELY)
                .exclude(ForecastIORequest.BLOCK_HOURLY)
                .exclude(ForecastIORequest.BLOCK_DAILY)
                .setLanguage(ForecastIORequest.LANGUAGE_ENGLISH)
                .build();


    }

    public static String formatString(String text) {

        StringBuilder json = new StringBuilder();
        String indentString = "";

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            switch (letter) {
                case '{':
                case '[':
                    json.append("\n").append(indentString).append(letter).append("\n");
                    indentString = indentString + "\t";
                    json.append(indentString);
                    break;
                case '}':
                case ']':
                    indentString = indentString.replaceFirst("\t", "");
                    json.append("\n").append(indentString).append(letter);
                    break;
                case ',':
                    json.append(letter).append("\n").append(indentString);
                    break;

                default:
                    json.append(letter);
                    break;
            }
        }

        return json.toString();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
