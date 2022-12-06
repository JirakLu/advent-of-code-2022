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

        for (String s : input) {
            ArrayList<String> used = new ArrayList<>();
            String[] split = s.split("");

            int start = 0;
            for (int i = 0; i < split.length; i++) {
                if (!used.contains(split[i])) {
                    used.add(split[i]);

                    if (used.size() == 14) {
                        System.out.println("Ahoj -> " + (i + 1));
                        break;
                    }
                } else {
                    used.clear();

                    i = start;
                    start++;
                }
            }
        }
    }
}