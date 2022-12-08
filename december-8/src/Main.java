import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

        int[][] grid = new int[input.size()][input.get(0).length()];

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                grid[i][j] = Integer.parseInt(String.valueOf(input.get(i).charAt(j)));
            }
        }

        int total = 0;

        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                int point = grid[i][j];

                // get array of points in top direction
                HashSet<Integer> top = new HashSet<>();
                for (int k = 0; k < i; k++) {
                    top.add(grid[k][j]);
                }

                // get array of points in bottom direction
                HashSet<Integer> bottom = new HashSet<>();
                for (int k = i + 1; k < grid.length; k++) {
                    bottom.add(grid[k][j]);
                }

                // get array of points in left direction
                HashSet<Integer> left = new HashSet<>();
                for (int k = 0; k < j; k++) {
                    left.add(grid[i][k]);
                }

                // get array of points in right direction
                HashSet<Integer> right = new HashSet<>();
                for (int k = j + 1; k < grid[0].length; k++) {
                    right.add(grid[i][k]);
                }

                if (point > Collections.max(top) || point > Collections.max(bottom) || point > Collections.max(left) || point > Collections.max(right)) {
                    total++;
                }
            }
        }

        total += (grid.length - 1) * 4;
        System.out.println(total);

        int total2 = 0;

        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                int point = grid[i][j];

                // get array of points in top direction
                int[] top = new int[i];
                for (int k = 0; k < i; k++) {
                    top[k] = grid[k][j];
                }

                // get array of points in bottom direction
                int[] bottom = new int[grid.length - i - 1];
                for (int k = i + 1; k < grid.length; k++) {
                    bottom[k - i - 1] = grid[k][j];
                }

                // get array of points in left direction
                int[] left = new int[j];
                for (int k = 0; k < j; k++) {
                    left[k] = grid[i][k];
                }

                // get array of points in right direction
                int[] right = new int[grid[0].length - j - 1];
                for (int k = j + 1; k < grid[0].length; k++) {
                    right[k - j - 1] = grid[i][k];
                }

                // reverse left
                int[] left2 = new int[left.length];
                for (int k = 0; k < left.length; k++) {
                    left2[k] = left[left.length - k - 1];
                }

                // reverse top
                int[] top2 = new int[top.length];
                for (int k = 0; k < top.length; k++) {
                    top2[k] = top[top.length - k - 1];
                }

                int topCount = 0;
                for (int k = 0; k < top2.length; k++) {
                    if (point <= top2[k]) {
                        topCount++;
                        break;
                    }

                    topCount++;
                }

                int bottomCount = 0;
                for (int k = 0; k < bottom.length; k++) {
                    if (point <= bottom[k]) {
                        bottomCount++;
                        break;
                    }

                    bottomCount++;
                }

                int leftCount = 0;
                for (int k = 0; k < left2.length; k++) {
                    if (point <= left2[k]) {
                        leftCount++;
                        break;
                    }

                    leftCount++;
                }

                int rightCount = 0;
                for (int k = 0; k < right.length; k++) {
                    if (point <= right[k]) {
                        rightCount++;
                        break;
                    }

                    rightCount++;
                }

                int count = topCount * bottomCount * leftCount * rightCount;

                if (count > total2) {
                    total2 = count;
                }
            }
        }

        System.out.println(total2);
    }
}