import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static final String ENEMY_ROCK = "A";
    static final String ENEMY_PAPER = "B";
    static final String ENEMY_SCISSORS = "C";

    static final String ME_ROCK = "X";
    static final String ME_PAPER = "Y";
    static final String ME_SCISSORS = "Z";

    static final int ROCK = 1;
    static final int PAPER = 2;
    static final int SCISSORS = 3;

    static final int LOSS = 0;
    static final int DRAW = 3;
    static final int WIN = 6;

    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>();

        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                input.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HashMap<String, Integer> POINTS_MAP = new HashMap<>();
        POINTS_MAP.put(ME_ROCK, ROCK);
        POINTS_MAP.put(ME_PAPER, PAPER);
        POINTS_MAP.put(ME_SCISSORS, SCISSORS);

        int totalFirst = 0;

        for (String s : input) {
            String[] split = s.split(" ");
            String enemyMove = split[0];
            String myMove = split[1];

            if (Objects.equals(myMove, ME_ROCK) && Objects.equals(enemyMove, ENEMY_ROCK) || Objects.equals(myMove, ME_PAPER) && Objects.equals(enemyMove, ENEMY_PAPER) || Objects.equals(myMove, ME_SCISSORS) && Objects.equals(enemyMove, ENEMY_SCISSORS)) {
                totalFirst += DRAW;
            } else if (Objects.equals(myMove, ME_ROCK) && Objects.equals(enemyMove, ENEMY_PAPER)) {
                totalFirst += LOSS;
            } else if (Objects.equals(myMove, ME_PAPER) && Objects.equals(enemyMove, ENEMY_SCISSORS)) {
                totalFirst += LOSS;
            } else if (Objects.equals(myMove, ME_SCISSORS) && Objects.equals(enemyMove, ENEMY_ROCK)) {
                totalFirst += LOSS;
            } else {
                totalFirst += WIN;
            }

            totalFirst += POINTS_MAP.get(myMove);
        }

        System.out.println(totalFirst);


        int totalSecond = 0;

        for (String s : input) {
            String[] split = s.split(" ");
            String enemyMove = split[0];
            String outcome = split[1];

            // lose
            if (Objects.equals(outcome, "X")) {
                totalSecond += LOSS;
                if (Objects.equals(enemyMove, ENEMY_ROCK)) {
                    totalSecond += POINTS_MAP.get(ME_SCISSORS);
                } else if (Objects.equals(enemyMove, ENEMY_PAPER)) {
                    totalSecond += POINTS_MAP.get(ME_ROCK);
                } else if (Objects.equals(enemyMove, ENEMY_SCISSORS)) {
                    totalSecond += POINTS_MAP.get(ME_PAPER);
                }
                // draw
            } else if (Objects.equals(outcome, "Y")) {
                totalSecond += DRAW;
                if (Objects.equals(enemyMove, ENEMY_ROCK)) {
                    totalSecond += POINTS_MAP.get(ME_ROCK);
                } else if (Objects.equals(enemyMove, ENEMY_PAPER)) {
                    totalSecond += POINTS_MAP.get(ME_PAPER);
                } else if (Objects.equals(enemyMove, ENEMY_SCISSORS)) {
                    totalSecond += POINTS_MAP.get(ME_SCISSORS);
                }
                // win
            } else if (Objects.equals(outcome, "Z")) {
                totalSecond += WIN;
                if (Objects.equals(enemyMove, ENEMY_ROCK)) {
                    totalSecond += POINTS_MAP.get(ME_PAPER);
                } else if (Objects.equals(enemyMove, ENEMY_PAPER)) {
                    totalSecond += POINTS_MAP.get(ME_SCISSORS);
                } else if (Objects.equals(enemyMove, ENEMY_SCISSORS)) {
                    totalSecond += POINTS_MAP.get(ME_ROCK);
                }
            }

        }

        System.out.println(totalSecond);

    }

}