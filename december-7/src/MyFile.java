public class MyFile {
    
    private final String name;
    private final long size;
    
    public MyFile(String name, long size) {
        this.name = name;
        this.size = size;
    }
    
    public String getName() {
        return this.name;
    }
    
    public long getSize() {
        return this.size;
    }
    
}
