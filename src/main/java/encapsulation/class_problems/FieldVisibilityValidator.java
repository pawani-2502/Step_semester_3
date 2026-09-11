package encapsulation.class_problems;

public class FieldVisibilityValidator {

    public static class PatientRecord {
        private String patientId;
        String wardCode;
        protected double vitalsScore;
        public String facilityName;

        public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
            if (patientId == null || patientId.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid patientId");
            }
            this.patientId = patientId.trim();
            this.wardCode = wardCode;
            this.vitalsScore = vitalsScore;
            this.facilityName = facilityName;
        }

        public String getPatientId() {
            return patientId;
        }

        public String getWardCode() {
            return wardCode;
        }

        public double getVitalsScore() {
            return vitalsScore;
        }

        public String getFacilityName() {
            return facilityName;
        }
    }

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        String mod = fieldModifier.trim().toLowerCase();
        String ctx = accessorContext.trim().toUpperCase();

        if (mod.equals("public")) {
            return "ALLOWED";
        }

        if (ctx.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (mod.equals("private")) {
            return "DENIED";
        }

        if (ctx.equals("SAME_PACKAGE")) {
            if (mod.equals("default") || mod.equals("protected")) {
                return "ALLOWED";
            }
        }

        if (ctx.equals("DIFFERENT_PACKAGE")) {
            return "DENIED";
        }

        return "DENIED";
    }

    public static String summarizeBatch(String[][] attempts) {
        if (attempts == null) {
            return "Allowed: 0 | Denied: 0";
        }

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {
            if (attempt != null && attempt.length >= 2) {
                String result = classifyAccess(attempt[0], attempt[1]);
                if (result.equals("ALLOWED")) {
                    allowed++;
                } else {
                    denied++;
                }
            } else {
                denied++;
            }
        }

        return String.format("Allowed: %d | Denied: %d", allowed, denied);
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("default", "DIFFERENT_PACKAGE"));

        String[][] batch = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeBatch(batch));

        try {
            new PatientRecord("MT9", "W3", 98.2, "MediTrack Central");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}