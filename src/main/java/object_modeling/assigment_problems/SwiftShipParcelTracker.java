package object_modeling.assigment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SwiftShipParcelTracker {

    public enum ParcelStatus {
        BOOKED,
        PICKED_UP,
        IN_TRANSIT,
        OUT_FOR_DELIVERY,
        DELIVERED,
        CANCELLED;

        public boolean canTransitionTo(ParcelStatus target) {
            if (this == BOOKED) {
                return target == PICKED_UP || target == CANCELLED;
            }
            if (this == PICKED_UP) {
                return target == IN_TRANSIT;
            }
            if (this == IN_TRANSIT) {
                return target == OUT_FOR_DELIVERY;
            }
            if (this == OUT_FOR_DELIVERY) {
                return target == DELIVERED;
            }
            return false;
        }
    }

    public interface ShippingType {
        String getName();
        double calculateCharge(double weightKg);
    }

    public static class StandardShipping implements ShippingType {
        @Override
        public String getName() {
            return "Standard";
        }

        @Override
        public double calculateCharge(double weightKg) {
            return 40.0 + (10.0 * weightKg);
        }
    }

    public static class ExpressShipping implements ShippingType {
        @Override
        public String getName() {
            return "Express";
        }

        @Override
        public double calculateCharge(double weightKg) {
            return 80.0 + (15.0 * weightKg);
        }
    }

    public static class FragileShipping implements ShippingType {
        private StandardShipping standard = new StandardShipping();

        @Override
        public String getName() {
            return "Fragile";
        }

        @Override
        public double calculateCharge(double weightKg) {
            return standard.calculateCharge(weightKg) + 50.0;
        }
    }

    public interface NotificationChannel {
        void notifyStatus(String parcelId, ParcelStatus status);
    }

    public static class SmsChannel implements NotificationChannel {
        @Override
        public void notifyStatus(String parcelId, ParcelStatus status) {
            System.out.printf("[SMS] %s is now %s.%n", parcelId, status);
        }
    }

    public static class EmailChannel implements NotificationChannel {
        @Override
        public void notifyStatus(String parcelId, ParcelStatus status) {
            System.out.printf("[Email] %s is now %s.%n", parcelId, status);
        }
    }

    public static class Parcel {
        private String parcelId;
        private double weightKg;
        private ShippingType shippingType;
        private ParcelStatus status;
        private List<NotificationChannel> channels;

        public Parcel(String parcelId, double weightKg, ShippingType shippingType) {
            this.parcelId = parcelId;
            this.weightKg = weightKg;
            this.shippingType = shippingType;
            this.status = ParcelStatus.BOOKED;
            this.channels = new ArrayList<>();
        }

        public void subscribe(NotificationChannel channel) {
            if (channel != null && !channels.contains(channel)) {
                channels.add(channel);
            }
        }

        public void notifySubscribers() {
            for (NotificationChannel channel : channels) {
                channel.notifyStatus(parcelId, status);
            }
        }

        public String book() {
            double charge = shippingType.calculateCharge(weightKg);
            String info = String.format(Locale.US, "Parcel %s booked (%s, %d kg). Charge: \u20b9%.2f.",
                    parcelId, shippingType.getName(), (long) weightKg, charge);
            System.out.println(info);
            notifySubscribers();
            return info;
        }

        public String updateStatus(ParcelStatus newStatus) {
            if (!this.status.canTransitionTo(newStatus)) {
                String error = String.format("Invalid transition: %s \u2192 %s is not allowed.", this.status, newStatus);
                System.out.println(error);
                return error;
            }
            this.status = newStatus;
            notifySubscribers();
            return "Status updated to " + newStatus;
        }

        public String cancel() {
            if (this.status != ParcelStatus.BOOKED) {
                String error = String.format("Cancellation failed: %s can be cancelled only while BOOKED.", parcelId);
                System.out.println(error);
                return error;
            }
            this.status = ParcelStatus.CANCELLED;
            notifySubscribers();
            return "Parcel " + parcelId + " cancelled.";
        }

        public String getParcelId() {
            return parcelId;
        }

        public ParcelStatus getStatus() {
            return status;
        }
    }

    public static void main(String[] args) {
        Parcel p = new Parcel("P101", 2.0, new ExpressShipping());
        p.subscribe(new SmsChannel());
        p.subscribe(new EmailChannel());

        p.book();
        p.updateStatus(ParcelStatus.PICKED_UP);
        p.cancel();
        p.updateStatus(ParcelStatus.IN_TRANSIT);
        p.updateStatus(ParcelStatus.DELIVERED);
    }
}