package object_modeling.assigment_problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CampusCanteenSmartCard {

    public interface PricingPlan {
        String getPlanName();
        double calculateDiscountedPrice(double originalPrice);
    }

    public static class DayScholarPlan implements PricingPlan {
        @Override
        public String getPlanName() {
            return "Day Scholar";
        }

        @Override
        public double calculateDiscountedPrice(double originalPrice) {
            return originalPrice;
        }
    }

    public static class HostellerPlan implements PricingPlan {
        @Override
        public String getPlanName() {
            return "Hosteller";
        }

        @Override
        public double calculateDiscountedPrice(double originalPrice) {
            return originalPrice * 0.90;
        }
    }

    public static class StaffPlan implements PricingPlan {
        @Override
        public String getPlanName() {
            return "Staff";
        }

        @Override
        public double calculateDiscountedPrice(double originalPrice) {
            return originalPrice * 0.80;
        }
    }

    public enum CardState {
        ACTIVE,
        BLOCKED
    }

    public static class Transaction {
        private double amount;
        private String description;

        public Transaction(double amount, String description) {
            this.amount = amount;
            this.description = description;
        }

        public double getAmount() {
            return amount;
        }

        public String getDescription() {
            return description;
        }

        public String getFormattedSignedAmount() {
            if (amount >= 0) {
                return String.format(Locale.US, "+%.2f", amount);
            } else {
                return String.format(Locale.US, "-%.2f", Math.abs(amount));
            }
        }
    }

    public static class SmartCard {
        private String cardId;
        private PricingPlan plan;
        private CardState state;
        private double balance;
        private List<Transaction> transactions;
        private Set<String> refundedItems;
        private List<Transaction> purchaseTransactions;

        public SmartCard(String cardId, PricingPlan plan) {
            this.cardId = cardId;
            this.plan = plan;
            this.state = CardState.ACTIVE;
            this.balance = 0.0;
            this.transactions = new ArrayList<>();
            this.refundedItems = new HashSet<>();
            this.purchaseTransactions = new ArrayList<>();
        }

        public String getCardId() {
            return cardId;
        }

        public double getBalance() {
            return balance;
        }

        public CardState getState() {
            return state;
        }

        public void setBlockState(CardState state) {
            this.state = state;
        }

        public String topUp(double amount) {
            if (this.state == CardState.BLOCKED) {
                return "Top-up failed: Card is blocked.";
            }
            if (amount < 100.0) {
                return "Top-up failed: Minimum top-up is \u20b9100.00.";
            }
            if (this.balance + amount > 5000.0) {
                return "Top-up failed: Maximum card balance is \u20b95,000.00.";
            }

            this.balance += amount;
            transactions.add(new Transaction(amount, "Top-up"));
            return String.format(Locale.US, "%s topped up with \u20b9%.2f. Balance: \u20b9%.2f.",
                    cardId, amount, balance);
        }

        public String purchase(String itemName, double originalPrice) {
            if (this.state == CardState.BLOCKED) {
                return "Purchase failed: Card is blocked.";
            }

            double finalPrice = plan.calculateDiscountedPrice(originalPrice);
            if (this.balance < finalPrice) {
                return String.format(Locale.US, "Purchase failed: Insufficient balance (required \u20b9%.2f, available \u20b9%.2f).",
                        finalPrice, balance);
            }

            this.balance -= finalPrice;
            Transaction tx = new Transaction(-finalPrice, itemName);
            transactions.add(tx);
            purchaseTransactions.add(tx);

            return String.format(Locale.US, "%s purchased for \u20b9%.2f. Balance: \u20b9%.2f.",
                    itemName, finalPrice, balance);
        }

        public String refund(String itemName) {
            if (refundedItems.contains(itemName)) {
                return String.format("Refund rejected: %s has already been refunded.", itemName);
            }

            Transaction targetPurchase = null;
            for (Transaction tx : purchaseTransactions) {
                if (tx.getDescription().equals(itemName)) {
                    targetPurchase = tx;
                    break;
                }
            }

            if (targetPurchase == null) {
                return String.format("Refund rejected: No purchase record found for %s.", itemName);
            }

            double refundAmount = Math.abs(targetPurchase.getAmount());
            this.balance += refundAmount;
            transactions.add(new Transaction(refundAmount, "Refund: " + itemName));
            refundedItems.add(itemName);

            return String.format(Locale.US, "Refund of \u20b9%.2f for %s processed. Balance: \u20b9%.2f.",
                    refundAmount, itemName, balance);
        }

        public String getMiniStatement() {
            List<String> formatted = new ArrayList<>();
            for (Transaction tx : transactions) {
                formatted.add(tx.getFormattedSignedAmount());
            }
            String joined = String.join(", ", formatted);
            return String.format(Locale.US, "Mini-statement for %s: %s = \u20b9%.2f.",
                    cardId, joined, balance);
        }
    }

    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());

        System.out.println(card.topUp(500.0));
        System.out.println(card.purchase("Veg Thali", 120.0));
        System.out.println(card.purchase("Cold Coffee", 60.0));
        System.out.println(card.purchase("Assorted Items", 400.0));
        System.out.println(card.refund("Veg Thali"));
        System.out.println(card.refund("Veg Thali"));
        System.out.println(card.getMiniStatement());
    }
}