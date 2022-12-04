import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
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

        int total = 0;

        for (String s : input) {
            String[] split = s.split(",");

            String[] partOneSplit = split[0].split("-");
            int partOneStart = Integer.parseInt(partOneSplit[0]);
            int partOneEnd = Integer.parseInt(partOneSplit[1]);

            String[] partTwoSplit = split[1].split("-");
            int partTwoStart = Integer.parseInt(partTwoSplit[0]);
            int partTwoEnd = Integer.parseInt(partTwoSplit[1]);

            if ((partOneStart <= partTwoStart) && (partTwoEnd <= partOneEnd)) {
                total++;
            } else if ((partTwoStart <= partOneStart) && (partOneEnd <= partTwoEnd)) {
                total++;
            }

        }

        System.out.println(total);

        int totalSecond = 0;

        for (String s : input) {
            String[] split = s.split(",");

            String[] partOneSplit = split[0].split("-");
            int partOneStart = Integer.parseInt(partOneSplit[0]);
            int partOneEnd = Integer.parseInt(partOneSplit[1]);

            String[] partTwoSplit = split[1].split("-");
            int partTwoStart = Integer.parseInt(partTwoSplit[0]);
            int partTwoEnd = Integer.parseInt(partTwoSplit[1]);

            if ((partOneStart <= partTwoStart && partTwoStart <= partOneEnd) || (partOneStart <= partTwoEnd && partTwoEnd <= partOneEnd)) {
                totalSecond++;
            } else if ((partTwoStart <= partOneStart && partOneStart <= partTwoEnd) || (partTwoStart <= partOneEnd && partOneEnd <= partTwoEnd)) {
                totalSecond++;
            }

        }

        System.out.println(totalSecond);
    }
}