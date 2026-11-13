package abstraction.assigment_problems;

public class ConnectedHomeControlPanel {

    public interface RemoteControllable {
        String connect(String appId);
    }

    public interface EnergyTrackable {
        double getConsumptionWatts();
    }

    public static abstract class HomeDevice {
        private static int counter = 1000;
        private final String serialNumber;

        public HomeDevice() {
            counter++;
            this.serialNumber = "HD-" + counter;
        }

        public String getSerialNumber() {
            return serialNumber;
        }

        public abstract String activate();
    }

    public static class WashingMachine extends HomeDevice implements RemoteControllable, EnergyTrackable {
        private double consumptionWatts;

        public WashingMachine(double consumptionWatts) {
            super();
            this.consumptionWatts = consumptionWatts;
        }

        @Override
        public String activate() {
            return "Washing machine " + getSerialNumber() + " started a cycle";
        }

        @Override
        public String connect(String appId) {
            return getSerialNumber() + " connected to " + appId;
        }

        @Override
        public double getConsumptionWatts() {
            return consumptionWatts;
        }
    }

    public static class Refrigerator extends HomeDevice implements EnergyTrackable {
        private double consumptionWatts;

        public Refrigerator(double consumptionWatts) {
            super();
            this.consumptionWatts = consumptionWatts;
        }

        @Override
        public String activate() {
            return "Refrigerator " + getSerialNumber() + " cooling started";
        }

        @Override
        public double getConsumptionWatts() {
            return consumptionWatts;
        }
    }

    public static class MobileApp implements RemoteControllable {
        private String appName;

        public MobileApp(String appName) {
            this.appName = appName;
        }

        public String getAppName() {
            return appName;
        }

        @Override
        public String connect(String appId) {
            return appName + " connected to " + appId;
        }
    }

    public static void connectAll(RemoteControllable[] items, String appId) {
        if (items == null) {
            return;
        }
        for (RemoteControllable item : items) {
            if (item != null) {
                System.out.println(item.connect(appId));
            }
        }
    }

    public static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            return ((EnergyTrackable) d).getConsumptionWatts();
        }
        return -1.0;
    }

    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine(500.0);
        System.out.println(wm.activate());
        System.out.println(wm.connect("HomeConnect"));

        Refrigerator fridge = new Refrigerator(150.0);
        System.out.println(getConsumptionIfTrackable(fridge));

        MobileApp app = new MobileApp("HomeConnect App");
        System.out.println(app.connect("HomeConnect"));

        HomeDevice ref = wm;
        System.out.println(getConsumptionIfTrackable(ref));
    }
}