package sk.upjs.paz1a.tutoring.exercise09;

/**
 * A dimmable light. The brightness is a percentage (0-100) and is remembered while the light is off.
 * <p>
 * {@link #getStatusReport()} must return exactly:
 * <pre>
 * Light living-room: ON, brightness 80%
 * Light living-room: OFF
 * </pre>
 * The fields {@code id} and {@code isOn} are private in {@link Device}, so use {@code getId()} and
 * {@code isOn()} here.
 */
public class Light extends Device {

    public static final int MIN_BRIGHTNESS = 0;
    public static final int MAX_BRIGHTNESS = 100;

    private int brightness;

    /**
     * Creates a light that is switched OFF.
     *
     * @throws IllegalArgumentException if id is null or blank, or brightness is outside 0..100
     */
    public Light(String id, int brightness) {
        super(id); // the Device part of the object is set up first
        checkBrightness(brightness);
        // TODO: Implement this
    }

    public int getBrightness() {
        // TODO: Implement this
        return 0;
    }

    /**
     * @throws IllegalArgumentException if brightness is outside 0..100 (the old value is kept)
     */
    public void setBrightness(int brightness) {
        checkBrightness(brightness);
        // TODO: Implement this
    }

    @Override
    public String getStatusReport() {
        // TODO: Implement this
        return null;
    }

    private static void checkBrightness(int brightness) {
        if (brightness < MIN_BRIGHTNESS || brightness > MAX_BRIGHTNESS) {
            throw new IllegalArgumentException("Brightness must be between " + MIN_BRIGHTNESS
                    + " and " + MAX_BRIGHTNESS + ", but was " + brightness);
        }
    }
}
