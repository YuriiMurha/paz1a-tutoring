package sk.upjs.paz1a.tutoring.exercise09;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all devices of a home. It only ever works with the abstract type {@link Device}, so it
 * keeps working when somebody invents a brand-new kind of device tomorrow - that is polymorphism.
 * Do not use {@code instanceof} in this class.
 */
public class SmartHomeHub {

    private final List<Device> devices = new ArrayList<>();

    /**
     * @throws IllegalArgumentException if device is null
     */
    public void addDevice(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Cannot add a null device");
        }
        // TODO: Implement this
    }

    public int getDeviceCount() {
        // TODO: Implement this
        return 0;
    }

    /**
     * Switches every device off.
     */
    public void turnOffAll() {
        // TODO: Implement this
    }

    /**
     * Returns a header line followed by the status report of every device, in the order the devices
     * were added. Lines are separated by {@code "\n"}, with no line break after the last line:
     * <pre>
     * Smart home - devices: 2, on: 1
     * - Light living-room: ON, brightness 80%
     * - Thermostat hallway: OFF
     * </pre>
     * An empty hub returns just the header: {@code Smart home - devices: 0, on: 0}
     */
    public String generateSummary() {
        // TODO: Implement this
        return null;
    }
}
