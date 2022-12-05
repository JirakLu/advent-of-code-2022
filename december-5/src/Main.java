import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // parse input from input.txt
        //input looks like this
        /*
                [D]
            [N] [C]
            [Z] [M] [P]
             1   2   3

            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
         */

        // parse input
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

        ArrayList<String> moves = new ArrayList<>();

        for (String s : input) {
            if (s.startsWith("move")) {
                moves.add(s);
            }
        }

        input.removeAll(moves);

        input.remove(input.size() - 1);

        String[] counts = input.remove(input.size() - 1).split(" ");
        int CONTAINER_COUNT = Integer.parseInt(counts[counts.length - 1]);


        Collections.reverse(input);

        HashMap<Integer, ArrayList<String>> containers = new HashMap<>();

        for (int i = 1; i <= CONTAINER_COUNT; i++) {
            containers.put(i, new ArrayList<>());
        }

        for (String s : input) {
            String[] tmp = s.split("");

            for (int i = 1; i < CONTAINER_COUNT * 4; i = i + 4) {
                if (i > tmp.length - 1) {
                    break;
                }

                if (!tmp[i].equals(" ")) {
                    int mapIndex = ((i - 1) / 4) + 1;
                    containers.get(mapIndex).add(tmp[i]);
                }


            }
        }

        for (String move : moves) {
            String[] tmp = move.split(" ");

            int count = Integer.parseInt(tmp[1]);
            int from = Integer.parseInt(tmp[3]);
            int to = Integer.parseInt(tmp[5]);


            ArrayList<String> tmp2 = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                tmp2.add(containers.get(from).remove(containers.get(from).size() - 1));
            }

            // one by one
            // all at once
            Collections.reverse(tmp2);

            containers.get(to).addAll(tmp2);

        }

        for (int i = 1; i <= CONTAINER_COUNT; i++) {
            System.out.print(containers.get(i).get(containers.get(i).size() - 1));
        }
    }
}