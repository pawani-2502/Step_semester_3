package encapsulation.class_problems;

public class CrossPackageInheritanceReach {

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
            return "DENIED";
        }

        if (ctx.equals("DIFFERENT_PACKAGE")) {
            return "DENIED";
        }

        if (ctx.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
            if (mod.equals("protected")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        if (ctx.equals("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {
            return "DENIED";
        }

        return "DENIED";
    }

    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.trim().isEmpty()) {
            return "";
        }

        String[] parts = accessorContext.trim().split("_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            String word = parts[i];
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    sb.append(word.substring(1).toLowerCase());
                }
                if (i < parts.length - 1) {
                    sb.append(" ");
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}