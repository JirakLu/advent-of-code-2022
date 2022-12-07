import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
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

        Folder root = new Folder("/");
        Folder currentFolder = root;

        for (String line : input) {
            if (line.startsWith("$")) {
                line = line.replace("$ ", "");

                if (line.equals("ls")) continue;

                String[] split = line.split(" ");

                String command = split[0];
                String path = split[1];

                if (command.equals("cd")) {
                    if (path.equals("..")) {
                        currentFolder = currentFolder.getParent();
                    } else if (path.equals("/")) {
                        currentFolder = root;
                    } else {
                        currentFolder = currentFolder.getFolders().get(path);
                    }
                }

                continue;
            }

            if (line.startsWith("dir")) {
                String dirname = line.replace("dir ", "");
                currentFolder.addFolder(new Folder(dirname, currentFolder));
            } else {
                String[] split = line.split(" ");
                String filename = split[1];
                long size = Long.parseLong(split[0]);

                currentFolder.addFile(new MyFile(filename, size));
            }
        }

        ArrayList<Folder> foldersSizes = root.getFoldersWithMaxSize(100000);

        long size = 0;
        for (Folder folder : foldersSizes) {
            size += folder.getSize();
        }

        System.out.println(size);

        final long MAX_SIZE = 70000000;
        final long NEEDED_SIZE = 30000000;

        final long FREE_SPACE = MAX_SIZE - root.getSize();

        ArrayList<Folder> allFolders = root.getAllFolders();
        allFolders.sort(Comparator.comparingLong(Folder::getSize));

        for (Folder folder: allFolders) {
            if (folder.getSize() > (NEEDED_SIZE - FREE_SPACE)) {
                System.out.println(folder.getSize());
                break;
            }
        }
    }
}