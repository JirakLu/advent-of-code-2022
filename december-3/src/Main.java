import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
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
            String firstHalf = s.substring(0, s.length() / 2);
            String secondHalf = s.substring(s.length() / 2);

            HashSet<Character> firstHalfSet = new HashSet<>();
            for (int i = 0; i < firstHalf.length(); i++) {
                firstHalfSet.add(firstHalf.charAt(i));
            }

            HashSet<Character> secondHalfSet = new HashSet<>();
            for (int i = 0; i < secondHalf.length(); i++) {
                secondHalfSet.add(secondHalf.charAt(i));
            }

            ArrayList<Character> duplicates = new ArrayList<>();

            for (Character c : firstHalfSet) {
                if (secondHalfSet.contains(c)) {
                    duplicates.add(c);
                }
            }

            for (Character c : duplicates) {
                int ascii = (int) c;

                if (ascii > 64 && ascii < 91) { // uppercase
                    total += ((ascii - 64) + 26);
                } else if (ascii > 96 && ascii < 123) { // lowercase
                    total += (ascii - 96);
                }
            }
        }

        System.out.println(total);

        int totalSecond = 0;

        for (int i = 0; i < input.size() - 1; i += 3) {
            String first = input.get(i);
            String second = input.get(i + 1);
            String third = input.get(i + 2);

            HashSet<Character> matches = new HashSet<>();

            for (int j = 0; j < first.length(); j++) {
                if (second.contains(first.charAt(j) + "") && third.contains(first.charAt(j) + "")) {
                    matches.add(first.charAt(j));
                }
            }

            for (Character c : matches) {
                int ascii = (int) c;

                if (ascii > 64 && ascii < 91) { // uppercase
                    totalSecond += ((ascii - 64) + 26);
                } else if (ascii > 96 && ascii < 123) { // lowercase
                    totalSecond += (ascii - 96);
                }
            }
        }

        System.out.println(totalSecond);
    }
}