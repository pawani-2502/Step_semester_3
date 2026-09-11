package string.class_problems;

public class RockPaperScissorsGame {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove == null || computerMove == null) {
            return "Invalid Move";
        }

        String p = playerMove.trim().toLowerCase();
        String c = computerMove.trim().toLowerCase();

        if (p.equals(c)) {
            return "Draw";
        }

        if ((p.equals("rock") && c.equals("scissors")) ||
            (p.equals("paper") && c.equals("rock")) ||
            (p.equals("scissors") && c.equals("paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        String[] computerMoves = {"Scissors", "Paper", "Rock", "Scissors", "Scissors"};

        int wins = 0;
        int losses = 0;
        int draws = 0;
        int totalRounds = playerMoves.length;

        String[] results = new String[totalRounds];

        for (int i = 0; i < totalRounds; i++) {
            results[i] = playRound(playerMoves[i], computerMoves[i]);
            System.out.printf("Round %d - Player: %s, Computer: %s -> %s%n",
                    (i + 1), playerMoves[i], computerMoves[i], results[i]);

            if (results[i].equals("Player Wins")) {
                wins++;
            } else if (results[i].equals("Computer Wins")) {
                losses++;
            } else if (results[i].equals("Draw")) {
                draws++;
            }
        }

        System.out.println("\n-------------------------------------------------------------");
        System.out.printf("%-8s | %-12s | %-14s | %-14s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < totalRounds; i++) {
            System.out.printf("%-8d | %-12s | %-14s | %-14s%n",
                    (i + 1), playerMoves[i], computerMoves[i], results[i]);
        }
        System.out.println("-------------------------------------------------------------");

        double winPercentage = totalRounds > 0 ? ((double) wins / totalRounds) * 100.0 : 0.0;
        System.out.printf("Final Summary (after %d rounds)%n", totalRounds);
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                wins, losses, draws, winPercentage);
    }
}