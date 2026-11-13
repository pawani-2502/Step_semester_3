package object_modeling.assigment_problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CodeSprintJudgingDesk {

    public interface Track {
        String getName();
        double calculateScore(double idea, double execution, double presentation);
    }

    public static class InnovationTrack implements Track {
        @Override
        public String getName() {
            return "Innovation";
        }

        @Override
        public double calculateScore(double idea, double execution, double presentation) {
            return (idea * 0.50) + (execution * 0.30) + (presentation * 0.20);
        }
    }

    public static class OpenTrack implements Track {
        @Override
        public String getName() {
            return "Open";
        }

        @Override
        public double calculateScore(double idea, double execution, double presentation) {
            return (idea + execution + presentation) / 3.0;
        }
    }

    public static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Team {
        private String name;
        private List<Student> members;
        private Track track;
        private Project project;

        public Team(String name, List<Student> members, Track track) {
            this.name = name;
            this.members = members != null ? members : new ArrayList<>();
            this.track = track;
        }

        public String getName() {
            return name;
        }

        public List<Student> getMembers() {
            return members;
        }

        public Track getTrack() {
            return track;
        }

        public Project getProject() {
            return project;
        }

        public void setProject(Project project) {
            this.project = project;
        }
    }

    public static class Project {
        private String name;
        private Team team;

        public Project(String name, Team team) {
            this.name = name;
            this.team = team;
        }

        public String getName() {
            return name;
        }

        public Team getTeam() {
            return team;
        }
    }

    public static class Judge {
        private String name;

        public Judge(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum HackathonState {
        OPEN,
        JUDGING,
        PUBLISHED
    }

    public static class Hackathon {
        private String name;
        private HackathonState state;
        private List<Team> teams;
        private Set<String> registeredStudents;

        public Hackathon(String name) {
            this.name = name;
            this.state = HackathonState.OPEN;
            this.teams = new ArrayList<>();
            this.registeredStudents = new HashSet<>();
        }

        public String registerTeam(Team team) {
            if (team == null) {
                return "Registration failed: Invalid team.";
            }

            if (team.getMembers().size() < 2 || team.getMembers().size() > 4) {
                return "Registration failed: A team must have 2 to 4 members.";
            }

            for (Student s : team.getMembers()) {
                if (registeredStudents.contains(s.getName())) {
                    return "Registration failed: Student " + s.getName() + " is already in a team.";
                }
            }

            for (Student s : team.getMembers()) {
                registeredStudents.add(s.getName());
            }

            teams.add(team);
            return String.format("Team %s registered (%d members, %s track).",
                    team.getName(), team.getMembers().size(), team.getTrack().getName());
        }

        public String submitProject(Team team, Project project) {
            if (team == null || project == null) {
                return "Submission failed.";
            }
            if (team.getProject() != null) {
                return "Submission failed: Team already submitted a project.";
            }
            team.setProject(project);
            this.state = HackathonState.JUDGING;
            return String.format("Project '%s' submitted by %s.", project.getName(), team.getName());
        }

        public String scoreProject(Judge judge, Project project, double idea, double execution, double presentation) {
            if (this.state == HackathonState.PUBLISHED) {
                return "Rescore rejected: Results have already been published.";
            }
            double finalScore = project.getTeam().getTrack().calculateScore(idea, execution, presentation);
            return String.format(Locale.US, "Score recorded for '%s'. Final score: %.2f.", project.getName(), finalScore);
        }

        public String publishResults() {
            this.state = HackathonState.PUBLISHED;
            return "Results published.";
        }
    }

    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon("Code Sprint");

        List<Student> byteBustersMembers = new ArrayList<>();
        byteBustersMembers.add(new Student("Asha"));
        byteBustersMembers.add(new Student("Ravi"));
        byteBustersMembers.add(new Student("Neha"));
        Team byteBusters = new Team("ByteBusters", byteBustersMembers, new InnovationTrack());

        List<Student> soloCoderMembers = new ArrayList<>();
        soloCoderMembers.add(new Student("Kiran"));
        Team soloCoder = new Team("SoloCoder", soloCoderMembers, new OpenTrack());

        System.out.println(hackathon.registerTeam(byteBusters));
        System.out.println(hackathon.registerTeam(soloCoder));

        Project smartAttend = new Project("SmartAttend", byteBusters);
        System.out.println(hackathon.submitProject(byteBusters, smartAttend));

        Judge judge = new Judge("Chief Judge");
        System.out.println(hackathon.scoreProject(judge, smartAttend, 8, 7, 9));
        System.out.println(hackathon.publishResults());
        System.out.println(hackathon.scoreProject(judge, smartAttend, 10, 7, 9));
    }
}