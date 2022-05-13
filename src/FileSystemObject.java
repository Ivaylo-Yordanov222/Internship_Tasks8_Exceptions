public class FileSystemObject {
    private String parent;
    private String name;

    public FileSystemObject(String parent,String name){
        this.parent = parent;
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
