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

package com.tanmay.weatherdroidlib.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tanmay on 7/15/2016.
 */
public class UnitConvert {

    public static Map<Integer, String> DISTANCE_UNITS;
    public static final int KILOMETERS = 0;
    public static final int MILES = 1;
    public static final int NAUTICAL_MILES = 2;
    public static final int FEET = 3;
    public static final int LIGHT_YEAR = 4;
    public static final int ANGSTROM = 5;

    static {
        Map<Integer, String> units = new HashMap<>();
        units.put(KILOMETERS, Distance.KILOMETERS);
        units.put(MILES, Distance.MILES);
        units.put(NAUTICAL_MILES, Distance.NAUTICAL_MILES);
        units.put(FEET, Distance.FEET);
        units.put(LIGHT_YEAR, Distance.LIGHT_YEAR);
        units.put(ANGSTROM, Distance.ANGSTROM);
        DISTANCE_UNITS = Collections.unmodifiableMap(units);
    }

    public static Map<Integer, String> SPEED_UNITS;
    public static final int KPH = 0;
    public static final int MPS = 1;
    public static final int MPH = 3;
    public static final int KNOT = 4;
    public static final int BEAUFORT = 5;

    static {
        Map<Integer, String> units = new HashMap<>();
        units.put(KPH, Speed.KPH);
        units.put(MPS, Speed.MPS);
        units.put(MPH, Speed.MPH);
        units.put(KNOT, Speed.KNOTS);
        units.put(BEAUFORT, Speed.BEAUFORT);
        SPEED_UNITS = Collections.unmodifiableMap(units);
    }

    public static Map<Integer, String> TEMPERATURE_UNITS;
    public static final int CELSIUS = 0;
    public static final int FAHRENHEIT = 1;
    public static final int KELVIN = 2;
    public static final int RANKINE = 3;

    static {
        Map<Integer, String> units = new HashMap<>();
        units.put(CELSIUS, Temperature.CELSIUS);
        units.put(FAHRENHEIT, Temperature.FAHRENHEIT);
        units.put(KELVIN, Temperature.KELVIN);
        units.put(RANKINE, Temperature.RANKINE);
        TEMPERATURE_UNITS = Collections.unmodifiableMap(units);
    }

    public static Map<Integer, String> PRESSURE_UNITS;
    public static final int HECTOPASCAL = 0;
    public static final int PASCAL = 1;
    public static final int BAR = 2;
    public static final int ATMOSPHERE = 3;
    public static final int MM_MERCURY = 4;
    public static final int MM_WATER = 5;
    public static final int PSI = 6;
    public static final int TORR = 7;

    static {
        Map<Integer, String> units = new HashMap<>();
        units.put(HECTOPASCAL, Pressure.HECTOPASCAL);
        units.put(PASCAL, Pressure.PASCAL);
        units.put(BAR, Pressure.BAR);
        units.put(ATMOSPHERE, Pressure.ATMOSPHERE);
        units.put(MM_MERCURY, Pressure.MM_MERCURY);
        units.put(MM_WATER, Pressure.MM_WATER);
        units.put(PSI, Pressure.PSI);
        units.put(TORR, Pressure.TORR);
        PRESSURE_UNITS = Collections.unmodifiableMap(units);
    }

    public static Map<Integer, String> PRECIPITATION_HEIGHT_UNITS;
    public static final int MILLIMETERS = 0;
    public static final int INCHES = 1;
    public static final int CENTIMETERS = 2;

    static {
        Map<Integer, String> units = new HashMap<>();
        units.put(MILLIMETERS, Precipitation.MM);
        units.put(INCHES, Precipitation.IN);
        units.put(CENTIMETERS, Precipitation.CM);
        PRECIPITATION_HEIGHT_UNITS = Collections.unmodifiableMap(units);
    }

    public static Map<Integer, String> PRECIPITATION_SPEED_UNITS;
    public static final int MMPH = 0;
    public static final int INPH = 1;
    public static final int CMPH = 2;

    static {
        Map<Integer, String> units = new HashMap<>();
        units.put(MMPH, Precipitation.MMPH);
        units.put(INPH, Precipitation.INPH);
        units.put(CMPH, Precipitation.CMPH);
        PRECIPITATION_SPEED_UNITS = Collections.unmodifiableMap(units);
    }

    public static double convertDistance(double distance, int outputUnit) {
        switch (outputUnit) {
            case KILOMETERS:
                return distance;
            case MILES:
                return distance * 0.6214;
            case NAUTICAL_MILES:
                return distance * 0.54;
            case FEET:
                return distance * 3281;
            case LIGHT_YEAR:
                return distance * 1.057e-13;
            case ANGSTROM:
                return distance * 1e13;
            default:
                return distance;
        }
    }

    public static double convertPrecipitationHeight(double height, int outputUnit) {
        switch (outputUnit) {
            case MILLIMETERS:
                return height;
            case INCHES:
                return height / 25.4;
            case CENTIMETERS:
                return height / 10;
            default:
                return height;
        }
    }

    public static double convertPrecipitationSpeed(double intensity, int outputUnit) {
        switch (outputUnit) {
            case MMPH:
                return intensity;
            case INPH:
                return intensity * 0.03937;
            case CMPH:
                return intensity / 10;
            default:
                return intensity;
        }
    }

    public static double convertSpeed(double speed, int outputUnit) {
        switch (outputUnit) {
            case KPH:
                return speed * 3.6;
            case MPS:
                return speed;
            case MPH:
                return speed * 2.237;
            case KNOT:
                return speed * 1.944;
            case BEAUFORT:
                return getBeaufortNumber(speed);
            default:
                return speed;
        }
    }

    public static double convertTemperature(double temperature, int outputUnit) {
        switch (outputUnit) {
            case CELSIUS:
                return temperature;
            case FAHRENHEIT:
                return (temperature * (9 / 5)) + 32;
            case KELVIN:
                return temperature + 273.15;
            case RANKINE:
                return (temperature * (9 / 5)) + 32 + 459.67;
            default:
                return temperature;
        }
    }

    public static double convertPressure(double pressure, int outputUnit) {
        switch (outputUnit) {
            case HECTOPASCAL:
                return pressure;
            case PASCAL:
                return pressure * 100;
            case BAR:
                return pressure * 0.001;
            case ATMOSPHERE:
                return pressure / 1013.25;
            case MM_MERCURY:
                return pressure * 0.02952998751;
            case MM_WATER:
                return pressure * 0.4014631332;
            case PSI:
                return pressure * 0.014503773795536;
            case TORR:
                return pressure / 1.333223684;
            default:
                return pressure;
        }
    }

    private static int getBeaufortNumber(double speed) {
        if (speed < 0.3) {
            return 0;
        } else if (speed < 1.5) {
            return 1;
        } else if (speed < 3.3) {
            return 2;
        } else if (speed < 5.5) {
            return 3;
        } else if (speed < 8) {
            return 4;
        } else if (speed < 10.8) {
            return 5;
        } else if (speed < 13.9) {
            return 6;
        } else if (speed < 17.2) {
            return 7;
        } else if (speed < 20.7) {
            return 8;
        } else if (speed < 24.5) {
            return 9;
        } else if (speed < 28.4) {
            return 10;
        } else if (speed < 32.6) {
            return 11;
        } else {
            return 12;
        }
    }

    public static boolean showDegrees(int outputUnit) {
        switch (outputUnit) {
            case CELSIUS:
                return true;
            case FAHRENHEIT:
                return true;
            case KELVIN:
                return false;
            case RANKINE:
                return true;
            default:
                return true;
        }
    }

    public class Temperature {
        public static final String CELSIUS = "°C";
        public static final String FAHRENHEIT = "°F";
        public static final String KELVIN = "K";
        public static final String RANKINE = "°R";
    }

    public class Distance {
        public static final String KILOMETERS = "km";
        public static final String MILES = "mi";
        public static final String NAUTICAL_MILES = "NM";
        public static final String FEET = "ft";
        public static final String LIGHT_YEAR = "ly";
        public static final String ANGSTROM = "Å";
    }

    public class Speed {
        public static final String KPH = "km/h";
        public static final String MPS = "m/s";
        public static final String MPH = "mi/h";
        public static final String KNOTS = "kn";
        public static final String BEAUFORT = "";
    }

    public class Pressure {
        public static final String HECTOPASCAL = "hPa";
        public static final String PASCAL = "Pa";
        public static final String BAR = "bar";
        public static final String ATMOSPHERE = "atm";
        public static final String MM_MERCURY = "mmHg";
        public static final String MM_WATER = "mm wg";
        public static final String PSI = "psi";
        public static final String TORR = "Torr";
    }

    public class Precipitation {
        public static final String MMPH = "mm/h";
        public static final String INPH = "in/h";
        public static final String CMPH = "cm/h";
        public static final String MM = "mm";
        public static final String IN = "in";
        public static final String CM = "cm";
    }


}
