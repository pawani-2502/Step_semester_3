package object_modeling.assigment_problems;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SmartLabControlPanel {

    public interface Capability {
        String getCapabilityName();
        boolean applySetting(String deviceName, Object value);
    }

    public static class PowerCapability implements Capability {
        private boolean state;

        public PowerCapability() {
            this.state = false;
        }

        @Override
        public String getCapabilityName() {
            return "Power";
        }

        @Override
        public boolean applySetting(String deviceName, Object value) {
            String val = value.toString().toUpperCase();
            if (val.equals("ON") || val.equals("TRUE")) {
                this.state = true;
                System.out.println(deviceName + ": ON.");
                return true;
            } else if (val.equals("OFF") || val.equals("FALSE")) {
                this.state = false;
                System.out.println(deviceName + ": OFF.");
                return true;
            }
            return false;
        }
    }

    public static class BrightnessCapability implements Capability {
        private int brightness;

        public BrightnessCapability() {
            this.brightness = 0;
        }

        @Override
        public String getCapabilityName() {
            return "Brightness";
        }

        @Override
        public boolean applySetting(String deviceName, Object value) {
            int level;
            if (value instanceof Number) {
                level = ((Number) value).intValue();
            } else {
                level = Integer.parseInt(value.toString());
            }

            if (level < 0 || level > 100) {
                System.out.printf("Rejected: %s brightness must be between 0%% and 100%%.%n", deviceName);
                return false;
            }
            this.brightness = level;
            System.out.printf("%s: brightness set to %d%%.%n", deviceName, level);
            return true;
        }
    }

    public static class TemperatureCapability implements Capability {
        private int temperature;

        public TemperatureCapability() {
            this.temperature = 24;
        }

        @Override
        public String getCapabilityName() {
            return "Temperature";
        }

        @Override
        public boolean applySetting(String deviceName, Object value) {
            int temp;
            if (value instanceof Number) {
                temp = ((Number) value).intValue();
            } else {
                temp = Integer.parseInt(value.toString());
            }

            if (temp < 16 || temp > 30) {
                System.out.printf("Rejected: %s temperature must be between 16\u00b0C and 30\u00b0C.%n", deviceName);
                return false;
            }
            this.temperature = temp;
            System.out.printf("%s: temperature set to %d\u00b0C.%n", deviceName, temp);
            return true;
        }
    }

    public static class Device {
        private String name;
        private Map<String, Capability> capabilities;

        public Device(String name) {
            this.name = name;
            this.capabilities = new LinkedHashMap<>();
        }

        public String getName() {
            return name;
        }

        public void addCapability(Capability capability) {
            if (capability != null) {
                capabilities.put(capability.getCapabilityName(), capability);
            }
        }

        public void addCapabilityWithNotice(Capability capability) {
            if (capability != null) {
                capabilities.put(capability.getCapabilityName(), capability);
                System.out.printf("%s: %s capability added.%n", name, capability.getCapabilityName());
            }
        }

        public boolean hasCapability(String capabilityName) {
            return capabilities.containsKey(capabilityName);
        }

        public boolean applySetting(String capabilityName, Object value) {
            Capability cap = capabilities.get(capabilityName);
            if (cap != null) {
                return cap.applySetting(name, value);
            }
            return false;
        }
    }

    public static class SceneStep {
        private String capabilityName;
        private Object value;

        public SceneStep(String capabilityName, Object value) {
            this.capabilityName = capabilityName;
            this.value = value;
        }

        public String getCapabilityName() {
            return capabilityName;
        }

        public Object getValue() {
            return value;
        }
    }

    public static class Scene {
        private String name;
        private List<SceneStep> steps;

        public Scene(String name) {
            this.name = name;
            this.steps = new ArrayList<>();
        }

        public void addStep(String capabilityName, Object value) {
            steps.add(new SceneStep(capabilityName, value));
        }

        public void execute(List<Device> devices) {
            System.out.printf("Scene '%s' started.%n", name);
            int actionsApplied = 0;

            for (SceneStep step : steps) {
                for (Device device : devices) {
                    if (device.hasCapability(step.getCapabilityName())) {
                        boolean applied = device.applySetting(step.getCapabilityName(), step.getValue());
                        if (applied) {
                            actionsApplied++;
                        }
                    }
                }
            }

            System.out.printf("Scene '%s' completed: %d actions applied.%n", name, actionsApplied);
        }
    }

    public static void main(String[] args) {
        Device labAc = new Device("Lab AC");
        labAc.addCapability(new PowerCapability());
        labAc.addCapability(new TemperatureCapability());

        Device ceilingLights = new Device("Ceiling Lights");
        ceilingLights.addCapability(new PowerCapability());
        ceilingLights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> labDevices = new ArrayList<>();
        labDevices.add(labAc);
        labDevices.add(ceilingLights);
        labDevices.add(projector);

        Scene lectureMode = new Scene("Lecture Mode");
        lectureMode.addStep("Power", "ON");
        lectureMode.addStep("Brightness", 40);
        lectureMode.addStep("Temperature", 24);

        lectureMode.execute(labDevices);

        labAc.applySetting("Temperature", 12);

        projector.addCapabilityWithNotice(new BrightnessCapability());
        projector.applySetting("Brightness", 70);
    }
}