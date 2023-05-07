package util;

//Util Functions for use with pixels

public class UtilMath {

    //clamps a value between min and max
    public static double clamp(double value, double min, double max){
        return Math.min(Math.max(value, min), max);
    }

    //rescales the value between min and max
    public static double relativeScale(double value, double originalMin, double originalMax, double min, double max){
        return value / (originalMax - originalMin / max - min);
    }

    public static double randomDouble(int rangeMin, int rangeMax){
        return Math.random() * (rangeMax - rangeMin) + rangeMin;
    }
    
}
