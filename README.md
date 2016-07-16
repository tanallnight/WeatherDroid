# All inclusive Weather Library for Android

### Install
Add to the app level `build.gradle`:
```java
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.tanallnight:WeatherDroid:0.0.1'
}
```

### Usage
##### Initialization
Currently two weather services are supported:
* `WeatherService.SERVICE_FORECAST_IO`
* `WeatherService.SERVICE_WEATHER_UNDERGROUND`

Extend the application class and initialize the `WeatherService` class by requesting activation of services using API KEYS
```java
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new WeatherService.Builder()
                .addService(WeatherService.SERVICE_FORECAST_IO, Keys.KEY_FORECAST_IO)
                .build();
    }
    ...
}
```

#### Retrieving Weather

##### ForecastIO

The weather from Forecast.io api can needs a `ForecastIORequest` object which contains parameters for the api request.
The following methods are **required** for the api to return successfully:
* `setLatitude();`
* `setLongitude();`
* `setUnits()`
###### Example
```java
ForecastIORequest request = new ForecastIORequest.Builder()
    .setLatitude(37.8267)
    .setLongtiude(-122.423)
    .setUnits(ForecastIORequest.UNITS_US)
    .setTime(1468639441);
    .exclude(ForecastIORequest.BLOCK_MINUTELY)
    .exclude(ForecastIORequest.BLOCK_FLAGS)
    .extendHourly()
    .setLanguage(ForecastIORequest.LANGUAGE_ENGLISH)
    .build();

ForecastIOApi.getInstance().getWeather(request, new ForecastIOApi.ForecastResponse() {
    @Override
    public void onSuccess(ForecastIOResponse response) {}

    @Override
    public void onFail(Throwable t) {}
});
```

#### Unit Conversion
The library comes with built in unit conversion methods for convenience. These methods can be accessed by using the `UnitConvert` class.

To use the `UnitConvert` class with the Forecast IO Api, the units for the api request in the builder **need** to be set to `ForecastIORequest.UNITS_LOCAL_CONVERT` or else the conversion done by the library might be unreliable.

To convert temperature using the `UnitConvert` class, the following method must be invoked
```java
convertTemperature(double temperature, int outputUnit)
```
Where temperature is the `temperature` from the api response and `outputUnit` can be one of the following:
* `UnitConvert.CELSIUS`
* `UnitConvert.FAHRENHEIT`
* `UnitConvert.KELVIN`
* `UnitConvert.RANKINE`

Strings formats of the units can be found in the `UnitConvert.Temperature`. For example to get `°C`, `UnitConvert.Temperature.CELSIUS` must be used.

Similar methods can be found for other weather related units such as:
* `Distance`
* `Speed`
* `Pressure`
* `Precipitation`


### License
```
Copyright 2016 Tanmay Parikh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```