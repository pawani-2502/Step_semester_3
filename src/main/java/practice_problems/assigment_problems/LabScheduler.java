package practice_problems.assigment_problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LabScheduler {

    public static class Job {
        private String name;
        private int remainingTime;

        public Job(String name, int remainingTime) {
            this.name = name;
            this.remainingTime = remainingTime;
        }

        public String getName() {
            return name;
        }

        public int getRemainingTime() {
            return remainingTime;
        }

        public void reduceTime(int time) {
            this.remainingTime -= time;
        }
    }

    public static void runSchedule(int quantum, List<Job> initialJobs) {
        Queue<Job> queue = new ArrayDeque<>(initialJobs);
        int currentTime = 0;
        List<String> timeSlices = new ArrayList<>();
        List<String> finishedJobs = new ArrayList<>();

        while (!queue.isEmpty()) {
            Job job = queue.poll();
            int slice = Math.min(quantum, job.getRemainingTime());
            int start = currentTime;
            int end = currentTime + slice;
            currentTime = end;
            job.reduceTime(slice);

            timeSlices.add(job.getName() + "(" + start + "-" + end + ")");

            if (job.getRemainingTime() > 0) {
                queue.offer(job);
            } else {
                finishedJobs.add(job.getName() + " " + currentTime);
            }
        }

        System.out.println(String.join(" ", timeSlices));
        System.out.println("finish " + String.join(", ", finishedJobs));
    }

    public static void main(String[] args) {
        int quantum = 3;
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job("P1", 5));
        jobs.add(new Job("P2", 3));
        jobs.add(new Job("P3", 7));

        runSchedule(quantum, jobs);
    }
}