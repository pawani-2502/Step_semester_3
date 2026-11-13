package abstraction.assigment_problems;

public class FleetMaintenanceTracker {

    public interface Insurable {
        String getInsuranceInfo();
    }

    public static abstract class ServiceableVehicle {
        private double mileage;

        public ServiceableVehicle() {
            this.mileage = 0.0;
        }

        public abstract String performMaintenance();

        public double getMileage() {
            return mileage;
        }

        public void addMileage(double km) {
            if (km < 0) {
                throw new IllegalArgumentException("Negative mileage rejected");
            }
            this.mileage += km;
        }
    }

    public static class Forklift extends ServiceableVehicle implements Insurable {
        protected String assetTag;

        public Forklift(String assetTag) {
            super();
            this.assetTag = assetTag;
        }

        public String getAssetTag() {
            return assetTag;
        }

        @Override
        public String performMaintenance() {
            return "Forklift " + assetTag + ": hydraulic and fork inspection complete";
        }

        @Override
        public String getInsuranceInfo() {
            return "Insured under fleet policy - Asset " + assetTag;
        }
    }

    public static class HeavyDutyForklift extends Forklift {
        public HeavyDutyForklift(String assetTag) {
            super(assetTag);
        }

        @Override
        public String performMaintenance() {
            return super.performMaintenance() + " | high-pressure hydraulic check complete";
        }
    }

    public static String getInsuranceIfApplicable(ServiceableVehicle v) {
        if (v instanceof Insurable) {
            return ((Insurable) v).getInsuranceInfo();
        }
        return "No insurance record exists";
    }

    public static void main(String[] args) {
        Forklift f = new Forklift("FL-22");
        f.addMileage(120);
        System.out.println(f.getMileage());

        System.out.println(f.performMaintenance());

        HeavyDutyForklift hd = new HeavyDutyForklift("HD-9");
        System.out.println(hd.performMaintenance());

        System.out.println(getInsuranceIfApplicable(f));
    }
}