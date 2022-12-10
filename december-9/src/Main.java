import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>();

        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Point HEAD = new Point(0, 4);
        Point TAIL = new Point(0, 4);

        HashSet<Point> visited = new HashSet<>();
        visited.add(new Point(0, 4));

        for (String s : input) {
            String[] split = s.split(" ");
            String direction = split[0];
            int distance = Integer.parseInt(split[1]);

            for (int i = 0; i < distance; i++) {
                switch (direction) {
                    case "R" -> HEAD.x += 1;
                    case "L" -> HEAD.x -= 1;
                    case "U" -> HEAD.y -= 1;
                    case "D" -> HEAD.y += 1;
                }

                Point vector = new Point(HEAD.x - TAIL.x, HEAD.y - TAIL.y);

                if (Math.abs(vector.x) > 1 || Math.abs(vector.y) > 1) {
                    TAIL.x += vector.x != 0 ? vector.x / Math.abs(vector.x) : 0;
                    TAIL.y += vector.y != 0 ? vector.y / Math.abs(vector.y) : 0;

                    visited.add(new Point(TAIL.x, TAIL.y));
                }
            }
        }

        System.out.println(visited.size());


        Point HEAD_2 = new Point(0, 4);

        ArrayList<Point> tails = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            tails.add(new Point(0, 4));
        }

        HashSet<Point> visited_2 = new HashSet<>();
        visited_2.add(tails.get(tails.size() - 1));

        for (String s : input) {
            String[] split = s.split(" ");
            String direction = split[0];
            int distance = Integer.parseInt(split[1]);

            for (int i = 0; i < distance; i++) {
                switch (direction) {
                    case "R" -> HEAD_2.x += 1;
                    case "L" -> HEAD_2.x -= 1;
                    case "U" -> HEAD_2.y -= 1;
                    case "D" -> HEAD_2.y += 1;
                }

                Point prev = HEAD_2;

                for (int j = 0; j < tails.size(); j++) {
                    Point TMP_TAIL = tails.get(j);

                    Point vector = new Point(prev.x - TMP_TAIL.x, prev.y - TMP_TAIL.y);

                    if (Math.abs(vector.x) > 1 || Math.abs(vector.y) > 1) {
                        TMP_TAIL.x += vector.x != 0 ? vector.x / Math.abs(vector.x) : 0;
                        TMP_TAIL.y += vector.y != 0 ? vector.y / Math.abs(vector.y) : 0;
                    }

                    if (j == tails.size() - 1) {
                        visited_2.add(new Point(TMP_TAIL.x, TMP_TAIL.y));
                    }

                    prev = TMP_TAIL;
                    tails.set(j, TMP_TAIL);
                }

            }
        }

        System.out.println(visited_2.size());
    }
}