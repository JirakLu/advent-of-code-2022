import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // read input.txt
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

        System.out.println(input);

        int total = 0;
        int maxTotal = 0;

        for (String s : input) {
            if (s.equals("")) {
                if (total > maxTotal) {
                    maxTotal = total;
                }
                total = 0;
            } else {
                total += Integer.parseInt(s);
            }
        }

        System.out.println(maxTotal);
    }
}