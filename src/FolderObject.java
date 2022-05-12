import java.util.List;

public class FolderObject extends FileSystemObject{
    private List<String> children;

    public FolderObject(String parent, String name, List<String> children) {
        super(parent, name);
        this.children = children;
    }
    public List<String> getChildren() {
        return children;
    }
}
