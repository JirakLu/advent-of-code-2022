import java.util.ArrayList;
import java.util.HashMap;

public class Folder {

    private final String name;
    private final HashMap<String, Folder> folders;
    private final HashMap<String, MyFile> files;
    private Folder parent;

    public Folder(String name) {
        this.name = name;
        this.folders = new HashMap<>();
        this.files = new HashMap<>();
    }

    public Folder(String name, Folder parent) {
        this.name = name;
        this.folders = new HashMap<>();
        this.files = new HashMap<>();
        this.parent = parent;
    }
    
    public void addFolder(Folder folder) {
        this.folders.put(folder.getName(), folder);
    }
    
    public void addFile(MyFile file) {
        this.files.put(file.getName(), file);
    }
    
    public String getName() {
        return this.name;
    }
    
    public HashMap<String, Folder> getFolders() {
        return this.folders;
    }
    
    public HashMap<String, MyFile> getFiles() {
        return this.files;
    }

    public Folder getParent() {
        return this.parent;
    }
    
    public long getSize() {
        long size = 0;
        for (MyFile file : this.files.values()) {
            size += file.getSize();
        }
        for (Folder folder : this.folders.values()) {
            size += folder.getSize();
        }
        return size;
    }

    public ArrayList<Folder> getFoldersWithMaxSize(int size) {
        ArrayList<Folder> folders = new ArrayList<>();
        if (this.getSize() < size) {
            folders.add(this);
        }
        for (Folder folder : this.folders.values()) {
            folders.addAll(folder.getFoldersWithMaxSize(size));
        }
        return folders;
    }

    public ArrayList<Folder> getAllFolders() {
        ArrayList<Folder> folders = new ArrayList<>();
        folders.add(this);
        for (Folder folder : this.folders.values()) {
            folders.addAll(folder.getAllFolders());
        }
        return folders;
    }

    public String toString() {
        return this.name + " " + this.getSize();
    }
}
