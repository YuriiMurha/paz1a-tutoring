package sk.upjs.paz1a.tutoring.exercise09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeTest {

    private static final double DELTA = 1e-9;

    // ---------- Device basics (tested through Light, because Device is abstract) ----------

    @Test
    void newDeviceIsOffAndKeepsItsId() {
        Light light = new Light("kitchen", 50);
        assertEquals("kitchen", light.getId());
        assertFalse(light.isOn());
    }

    @Test
    void deviceCanBeTurnedOnAndOff() {
        Device light = new Light("kitchen", 50); // a Light IS a Device

        light.turnOn();
        assertTrue(light.isOn());

        light.turnOff();
        assertFalse(light.isOn());
    }

    @Test
    void turningOnTwiceKeepsDeviceOn() {
        Device light = new Light("kitchen", 50);
        light.turnOn();
        light.turnOn();
        assertTrue(light.isOn(), "turnOn() switches the device on - it does not toggle");
    }

    @Test
    void deviceRejectsNullOrBlankId() {
        assertThrows(IllegalArgumentException.class, () -> new Light(null, 50));
        assertThrows(IllegalArgumentException.class, () -> new Thermostat("  ", 21.0));
    }

    // ---------- Light ----------

    @Test
    void lightStoresBrightness() {
        assertEquals(75, new Light("desk", 75).getBrightness());
    }

    @Test
    void lightAcceptsBrightnessLimits() {
        assertEquals(0, new Light("desk", 0).getBrightness());
        assertEquals(100, new Light("desk", 100).getBrightness());
    }

    @Test
    void lightRejectsBrightnessOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> new Light("desk", -1));
        assertThrows(IllegalArgumentException.class, () -> new Light("desk", 101));
    }

    @Test
    void setBrightnessChangesBrightness() {
        Light light = new Light("desk", 40);
        light.setBrightness(90);
        assertEquals(90, light.getBrightness());
    }

    @Test
    void setBrightnessRejectsInvalidValueAndKeepsOldOne() {
        Light light = new Light("desk", 40);
        assertThrows(IllegalArgumentException.class, () -> light.setBrightness(150));
        assertEquals(40, light.getBrightness());
    }

    @Test
    void lightStatusReportWhenOn() {
        Light light = new Light("living-room", 80);
        light.turnOn();
        assertEquals("Light living-room: ON, brightness 80%", light.getStatusReport());
    }

    @Test
    void lightStatusReportWhenOff() {
        assertEquals("Light living-room: OFF", new Light("living-room", 80).getStatusReport());
    }

    @Test
    void lightStatusReportAtZeroBrightness() {
        Light light = new Light("hall", 0);
        light.turnOn();
        assertEquals("Light hall: ON, brightness 0%", light.getStatusReport());
    }

    // ---------- Thermostat ----------

    @Test
    void thermostatStoresTargetTemperature() {
        assertEquals(21.5, new Thermostat("hallway", 21.5).getTargetTemperature(), DELTA);
    }

    @Test
    void thermostatAcceptsTemperatureLimits() {
        assertEquals(5.0, new Thermostat("garage", 5.0).getTargetTemperature(), DELTA);
        assertEquals(30.0, new Thermostat("sauna", 30.0).getTargetTemperature(), DELTA);
    }

    @Test
    void thermostatRejectsTemperatureOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> new Thermostat("garage", 4.9));
        assertThrows(IllegalArgumentException.class, () -> new Thermostat("sauna", 30.1));
    }

    @Test
    void setTargetTemperatureChangesTemperature() {
        Thermostat thermostat = new Thermostat("hallway", 20.0);
        thermostat.setTargetTemperature(22.5);
        assertEquals(22.5, thermostat.getTargetTemperature(), DELTA);
    }

    @Test
    void setTargetTemperatureRejectsInvalidValueAndKeepsOldOne() {
        Thermostat thermostat = new Thermostat("hallway", 20.0);
        assertThrows(IllegalArgumentException.class, () -> thermostat.setTargetTemperature(-3.0));
        assertEquals(20.0, thermostat.getTargetTemperature(), DELTA);
    }

    @Test
    void thermostatStatusReportWhenOn() {
        Thermostat thermostat = new Thermostat("hallway", 21.5);
        thermostat.turnOn();
        assertEquals("Thermostat hallway: ON, target 21.5 C", thermostat.getStatusReport());
    }

    @Test
    void thermostatStatusReportWhenOff() {
        assertEquals("Thermostat hallway: OFF", new Thermostat("hallway", 21.5).getStatusReport());
    }

    // ---------- polymorphism ----------

    @Test
    void sameMethodCallGivesDeviceSpecificReports() {
        Device[] devices = {new Light("lamp", 60), new Thermostat("bedroom", 19.0)};
        for (Device device : devices) {
            device.turnOn();
        }
        // the variable type is Device, but each object runs its own getStatusReport()
        assertEquals("Light lamp: ON, brightness 60%", devices[0].getStatusReport());
        assertEquals("Thermostat bedroom: ON, target 19.0 C", devices[1].getStatusReport());
    }

    // ---------- SmartHomeHub ----------

    @Test
    void emptyHub() {
        SmartHomeHub hub = new SmartHomeHub();
        assertEquals(0, hub.getDeviceCount());
        assertEquals("Smart home - devices: 0, on: 0", hub.generateSummary());
    }

    @Test
    void addDeviceIncreasesCount() {
        SmartHomeHub hub = new SmartHomeHub();
        hub.addDevice(new Light("lamp", 60));
        hub.addDevice(new Thermostat("bedroom", 19.0));
        assertEquals(2, hub.getDeviceCount());
    }

    @Test
    void addDeviceRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> new SmartHomeHub().addDevice(null));
    }

    @Test
    void turnOffAllSwitchesEveryDeviceOff() {
        SmartHomeHub hub = new SmartHomeHub();
        Light light = new Light("lamp", 60);
        Thermostat thermostat = new Thermostat("bedroom", 19.0);
        hub.addDevice(light);
        hub.addDevice(thermostat);
        light.turnOn();
        thermostat.turnOn();
        assertTrue(light.isOn() && thermostat.isOn(), "precondition: both devices are on");

        hub.turnOffAll();

        assertFalse(light.isOn());
        assertFalse(thermostat.isOn());
    }

    @Test
    void turnOffAllOnEmptyHubDoesNothing() {
        assertDoesNotThrow(() -> new SmartHomeHub().turnOffAll());
    }

    @Test
    void summaryListsDevicesInTheOrderTheyWereAdded() {
        SmartHomeHub hub = new SmartHomeHub();
        Light light = new Light("living-room", 80);
        hub.addDevice(light);
        hub.addDevice(new Thermostat("hallway", 21.5));
        light.turnOn();

        String expected = """
                Smart home - devices: 2, on: 1
                - Light living-room: ON, brightness 80%
                - Thermostat hallway: OFF""";
        assertEquals(expected, hub.generateSummary());
    }

    @Test
    void summaryAfterTurnOffAll() {
        SmartHomeHub hub = new SmartHomeHub();
        Light light = new Light("lamp", 60);
        hub.addDevice(light);
        light.turnOn();

        hub.turnOffAll();

        assertEquals("Smart home - devices: 1, on: 0\n- Light lamp: OFF", hub.generateSummary());
    }

    @Test
    void hubWorksWithAnyKindOfDevice() {
        // a device type the hub has never heard of - this works only thanks to polymorphism
        Device doorbell = new Device("front-door") {
            @Override
            public String getStatusReport() {
                return "Doorbell " + getId() + (isOn() ? ": ready" : ": silent");
            }
        };
        SmartHomeHub hub = new SmartHomeHub();
        hub.addDevice(doorbell);
        doorbell.turnOn();

        assertEquals("Smart home - devices: 1, on: 1\n- Doorbell front-door: ready", hub.generateSummary());
    }
}
