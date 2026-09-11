package encapsulation.class_problems;

public class ImmutableDischargeSummary {

    public static class DischargeSummary {
        private final String patientId;
        private final String[] medicationCodes;

        public DischargeSummary(String patientId, String[] medicationCodes) {
            if (patientId == null || patientId.trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid patientId");
            }
            if (medicationCodes == null) {
                throw new IllegalArgumentException("Medication codes cannot be null");
            }

            for (String code : medicationCodes) {
                if (code == null || code.length() != 5 || !code.startsWith("MED-") || !Character.isUpperCase(code.charAt(4))) {
                    throw new IllegalArgumentException("Invalid medication code format");
                }
            }

            this.patientId = patientId;
            this.medicationCodes = medicationCodes.clone();
        }

        public String getPatientId() {
            return patientId;
        }

        public String[] getMedicationCodes() {
            return medicationCodes.clone();
        }

        public DischargeSummary withCorrectedMedication(int index, String newCode) {
            if (newCode == null || newCode.length() != 5 || !newCode.startsWith("MED-") || !Character.isUpperCase(newCode.charAt(4))) {
                throw new IllegalArgumentException("Invalid medication code format");
            }
            if (index < 0 || index >= medicationCodes.length) {
                throw new IndexOutOfBoundsException("Invalid index");
            }

            String[] updated = medicationCodes.clone();
            updated[index] = newCode;
            return new DischargeSummary(this.patientId, updated);
        }
    }

    public static class CriticalCareDischargeSummary extends DischargeSummary {
        private final int icuDays;

        public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
            super(patientId, medicationCodes);
            this.icuDays = icuDays;
        }

        public int getIcuDays() {
            return icuDays;
        }
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        if (summaries == null) {
            return "0 processed | 0 null skipped | 0 critical-care | 0 routine";
        }

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCare++;
            } else {
                routine++;
            }
        }

        return String.format("%d processed | %d null skipped | %d critical-care | %d routine",
                processed, nullSkipped, criticalCare, routine);
    }

    public static void main(String[] args) {
        try {
            new DischargeSummary("MT2026-0142", new String[]{"MED-A", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        DischargeSummary d = new DischargeSummary("MT2026-0142", new String[]{"MED-A", "MED-B"});
        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";
        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary[] batch = {
            new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
            null,
            new DischargeSummary("MT002", new String[]{"MED-Y"})
        };
        System.out.println(processNightlyBatch(batch));
    }
}