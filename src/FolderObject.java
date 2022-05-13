import java.util.List;

public class FolderObject extends FileSystemObject{
    private final List<FileSystemObject> children;

    public FolderObject(String parent, String name, List<FileSystemObject> children) {
        super(parent, name);
        this.children = children;
    }
    public List<FileSystemObject> getChildren() {
        return children;
    }
}
