package encapsulation.class_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VitalsMonitoringGuard {

    public static class PatientVitals {
        private List<Double> readings;

        public PatientVitals() {
            this.readings = new ArrayList<>();
        }

        public PatientVitals(double[] initialReadings) {
            this();
            if (initialReadings != null) {
                for (double r : initialReadings) {
                    recordReading(r);
                }
            }
        }

        public void recordReading(double reading) {
            if (reading <= 0.0 || reading > 45.0) {
                return;
            }
            this.readings.add(reading);
        }

        public double getAverage() {
            if (readings.isEmpty()) {
                return 0.0;
            }
            double sum = 0.0;
            for (double r : readings) {
                sum += r;
            }
            return sum / readings.size();
        }

        public double[] getAllReadings() {
            double[] copy = new double[readings.size()];
            for (int i = 0; i < readings.size(); i++) {
                copy[i] = readings.get(i);
            }
            return copy;
        }
    }

    public static void main(String[] args) {
        PatientVitals v = new PatientVitals(new double[]{36.5, -2, 37.1});
        System.out.println(Arrays.toString(v.getAllReadings()));

        double[] copy = v.getAllReadings();
        copy[0] = 999;
        System.out.println(v.getAllReadings()[0]);
    }
}