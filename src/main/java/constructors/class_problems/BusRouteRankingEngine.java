package constructors.class_problems;

public class BusRouteRankingEngine {

    public static class BusRoute {
        private String routeCode;
        private String routeName;
        private int priority;

        public BusRoute(String routeCode, String routeName, int priority) {
            this.routeCode = routeCode;
            this.routeName = routeName;
            this.priority = priority;
        }

        public BusRoute(String routeCode, String routeName) {
            this(routeCode, routeName, 1);
        }

        public int compareTo(BusRoute other) {
            if (other == null) {
                return 1;
            }
            if (this.priority != other.priority) {
                return other.priority - this.priority;
            }
            int codeCompare = this.routeCode.compareToIgnoreCase(other.routeCode);
            if (codeCompare != 0) {
                return codeCompare;
            }
            return this.routeName.compareToIgnoreCase(other.routeName);
        }

        public String getRouteCode() {
            return routeCode;
        }

        public String getRouteName() {
            return routeName;
        }

        public int getPriority() {
            return priority;
        }
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null || routes.length <= 1) {
            return routes;
        }

        BusRoute[] sorted = routes.clone();
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - 1 - i; j++) {
                if (sorted[j].compareTo(sorted[j + 1]) > 0) {
                    BusRoute temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < ranked.length; i++) {
            sb.append("\"").append(ranked[i].getRouteCode()).append("\"");
            if (i < ranked.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}