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
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tanmay.weatherdroidlib.api.ForecastIOApi;
import com.tanmay.weatherdroidlib.models.forecastio.ForecastIORequest;
import com.tanmay.weatherdroidlib.models.forecastio.ForecastIOResponse;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.response);
        textView.setMovementMethod(new ScrollingMovementMethod());

        ForecastIORequest request = new ForecastIORequest.Builder()
                .setLatitude(37.8267)
                .setLongtiude(-122.423)
                .setUnits(ForecastIORequest.UNITS_SI)
                .exclude(ForecastIORequest.BLOCK_MINUTELY)
                .exclude(ForecastIORequest.BLOCK_FLAGS)
                .setLanguage(ForecastIORequest.LANGUAGE_ENGLISH)
                .build();

        ForecastIOApi.getInstance().getWeather(request, new ForecastIOApi.ForecastResponse() {
            @Override
            public void onSuccess(ForecastIOResponse response) {
                textView.setText(formatString(new Gson().toJson(response)));
            }

            @Override
            public void onFail(Throwable t) {

            }
        });

    }

    public static String formatString(String text) {

        StringBuilder json = new StringBuilder();
        String indentString = "";

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            switch (letter) {
                case '{':
                case '[':
                    json.append("\n" + indentString + letter + "\n");
                    indentString = indentString + "\t";
                    json.append(indentString);
                    break;
                case '}':
                case ']':
                    indentString = indentString.replaceFirst("\t", "");
                    json.append("\n" + indentString + letter);
                    break;
                case ',':
                    json.append(letter + "\n" + indentString);
                    break;

                default:
                    json.append(letter);
                    break;
            }
        }

        return json.toString();
    }
}
