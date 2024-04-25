package utils;
public class CalculationUtil {

    // Clamp function to restrict the scale within a range
    public static double clamp(double value, double min, double max) {
        return Math.min(max, Math.max(min, value));
    }

    public static double clamp(double value, double min) {
        return Math.max(value, min);
    }
}