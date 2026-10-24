package sk.upjs.paz1a.tutoring.exercise09;

/**
 * A heating thermostat with a target temperature in degrees Celsius (5.0-30.0).
 * <p>
 * {@link #getStatusReport()} must return exactly:
 * <pre>
 * Thermostat hallway: ON, target 21.5 C
 * Thermostat hallway: OFF
 * </pre>
 * Build the text with {@code +}: Java then writes 21.5 as "21.5" and 19 as "19.0".
 */
public class Thermostat extends Device {

    public static final double MIN_TEMPERATURE = 5.0;
    public static final double MAX_TEMPERATURE = 30.0;

    private double targetTemperature;

    /**
     * Creates a thermostat that is switched OFF.
     *
     * @throws IllegalArgumentException if id is null or blank, or the temperature is outside 5.0..30.0
     */
    public Thermostat(String id, double targetTemperature) {
        super(id);
        checkTemperature(targetTemperature);
        // TODO: Implement this
    }

    public double getTargetTemperature() {
        // TODO: Implement this
        return 0.0;
    }

    /**
     * @throws IllegalArgumentException if the temperature is outside 5.0..30.0 (the old value is kept)
     */
    public void setTargetTemperature(double targetTemperature) {
        checkTemperature(targetTemperature);
        // TODO: Implement this
    }

    @Override
    public String getStatusReport() {
        // TODO: Implement this
        return null;
    }

    private static void checkTemperature(double temperature) {
        if (temperature < MIN_TEMPERATURE || temperature > MAX_TEMPERATURE) {
            throw new IllegalArgumentException("Temperature must be between " + MIN_TEMPERATURE
                    + " and " + MAX_TEMPERATURE + ", but was " + temperature);
        }
    }
}
