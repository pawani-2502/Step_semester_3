package practice_problems.assigment_problems;

public class PlacementDriveShortlist {

    public static class Candidate {
        public int id;
        public String name;
        public int[] scores;
        public int total;
        public int bestClimb;
        public int startTest;
        public int endTest;

        public Candidate(int id, String name, int[] scores) {
            this.id = id;
            this.name = name;
            this.scores = scores;
            this.total = 0;
            for (int s : scores) {
                this.total += s;
            }
            computeBestClimb();
        }

        private void computeBestClimb() {
            int maxGain = 0;
            int bestStart = 1;
            int bestEnd = 1;

            for (int i = 1; i <= scores.length; i++) {
                for (int j = i + 1; j <= scores.length; j++) {
                    int gain = scores[j - 1] - scores[i - 1];
                    if (gain > maxGain) {
                        maxGain = gain;
                        bestStart = i;
                        bestEnd = j;
                    }
                }
            }

            this.bestClimb = maxGain;
            this.startTest = bestStart;
            this.endTest = bestEnd;
        }
    }

    public static void mergeSort(Candidate[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private static void merge(Candidate[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Candidate[] leftArr = new Candidate[n1];
        Candidate[] rightArr = new Candidate[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = array[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i].total >= rightArr[j].total) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            array[k++] = leftArr[i++];
        }
        while (j < n2) {
            array[k++] = rightArr[j++];
        }
    }

    public static int cutoffQuery(Candidate[] candidates, int x) {
        int low = 0;
        int high = candidates.length - 1;
        int count = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (candidates[mid].total >= x) {
                count = mid + 1;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return count;
    }

    public static String pairQuery(Candidate[] candidates, int target) {
        int left = 0;
        int right = candidates.length - 1;

        while (left < right) {
            int sum = candidates[left].total + candidates[right].total;
            if (sum == target) {
                return candidates[left].name + " + " + candidates[right].name;
            } else if (sum > target) {
                left++;
            } else {
                right--;
            }
        }
        return "no pair";
    }

    public static void main(String[] args) {
        Candidate[] candidates = new Candidate[] {
            new Candidate(1, "Asha", new int[]{60, 55, 70, 72, 68, 80}),
            new Candidate(2, "Ravi", new int[]{70, 72, 65, 60, 75, 78}),
            new Candidate(3, "Neha", new int[]{50, 65, 62, 70, 74, 84}),
            new Candidate(4, "Kiran", new int[]{80, 70, 66, 72, 70, 60}),
            new Candidate(5, "Mohan", new int[]{55, 60, 58, 64, 70, 73})
        };

        mergeSort(candidates, 0, candidates.length - 1);

        System.out.println("Shortlist:");
        for (int i = 0; i < candidates.length; i++) {
            Candidate c = candidates[i];
            System.out.println((i + 1) + ". " + c.name + " " + c.total + " | best climb +" + c.bestClimb + " (test " + c.startTest + " to " + c.endTest + ")");
        }

        System.out.println("CUTOFF 405: " + cutoffQuery(candidates, 405) + " candidates");
        System.out.println("CUTOFF 410: " + cutoffQuery(candidates, 410) + " candidates");
        System.out.println("PAIR 785: " + pairQuery(candidates, 785));
        System.out.println("PAIR 900: " + pairQuery(candidates, 900));
    }
}