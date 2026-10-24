package sk.upjs.paz1a.tutoring.exercise09;

/**
 * Exercise 09 - inheritance and polymorphism.
 * <p>
 * Every smart-home device has an id and can be switched on or off - that part is the same for all
 * devices, so it lives here. HOW a device describes its state differs, so
 * {@link #getStatusReport()} is abstract and every subclass writes its own version.
 * <p>
 * Because the class is abstract, {@code new Device(...)} is not allowed - only
 * {@code new Light(...)}, {@code new Thermostat(...)} and other subclasses.
 */
public abstract class Device {

    private String id;
    private boolean isOn;

    /**
     * Creates a device that is switched OFF.
     *
     * @throws IllegalArgumentException if id is null or blank
     */
    protected Device(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Device id must not be null or blank");
        }
        // TODO: Implement this
    }

    public String getId() {
        // TODO: Implement this
        return null;
    }

    public boolean isOn() {
        // TODO: Implement this
        return false;
    }

    public void turnOn() {
        // TODO: Implement this
    }

    public void turnOff() {
        // TODO: Implement this
    }

    /**
     * Returns a one-line description of the device, e.g. "Light kitchen: OFF".
     * Each subclass defines its exact format.
     */
    public abstract String getStatusReport();
}
