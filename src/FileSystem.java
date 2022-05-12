import java.util.ArrayList;
import java.util.List;

public final class FileSystem {
    private static final List<FileSystemObject> fileSystem = new ArrayList<>();

    public static List<FileSystemObject> getFileSystem() {
        return fileSystem;
    }

    public static FileSystemObject findObject(String name){
        FileSystemObject objectToFind = null;
        for(FileSystemObject obj : fileSystem){
            if(obj.getName().equals(name)){
                objectToFind = obj;
                break;
            }
        }
        return objectToFind;
    }

    public static void makeFileHandler(String[] commandLine) {
        if(commandLine.length == 1){
            throw new CustomFileSystemException("Invalid name specified");
        }
        String directoryName = commandLine[1];
        String[] splitDirectory = directoryName.split("/");
        String searchedDirectory =  getParent(splitDirectory);
        if(!Validator.checkDirectoryExists(searchedDirectory)){
            throw new CustomFileSystemException("Directory does not exist");
        }
        String fileName = splitDirectory[splitDirectory.length - 1];
        if(!Validator.checkIsFile(fileName)){
            throw new CustomFileSystemException("Illegal operation");
        }
        if(!Validator.validateDirectoryName(fileName) || !Validator.validateNameStartsWithSpecialChars(fileName)){
            throw new CustomFileSystemException("Invalid name specified");
        }
        if(!Validator.checkFileExtension(fileName)){
            throw new CustomFileSystemException("Invalid name specified");
        }
        FileObject file = new FileObject(searchedDirectory,fileName);
        fileSystem.add(file);
    }
    public static void makeDirectoryHandler(String[] commandLine) {
        if(commandLine.length == 1){
            throw new CustomFileSystemException("Invalid name specified");
        }
        String directoryName = commandLine[1];
        String[] subDirectories = directoryName.split("/");
        if(!Validator.validateDirectoryName(directoryName) || !Validator.validateNameStartsWithSpecialChars(directoryName)){
            throw new CustomFileSystemException("Invalid name specified");
        }

        String parent;
        String name;
        List<String> children = new ArrayList<>();
        if(subDirectories.length == 1){
            parent = directoryName;
            name = directoryName;
            makeDir(parent,name,children);
        }else{
            String searchedDirectory =  getParent(subDirectories);
            if(!Validator.checkDirectoryExists(searchedDirectory)){
                throw new CustomFileSystemException("Directory does not exist");
            }
            name = directoryName;
            parent = getParent(subDirectories);
            children = fillSubDirectories(subDirectories,1);
            makeDir(parent,name,children);
        }
    }
    private static String getParent(String[] subDirectories){
        StringBuilder sb = new StringBuilder();
        String parent;
        for(int i = 0; i < subDirectories.length - 1; i++){
            sb.append(subDirectories[i] + "/");
        }
        parent = sb.toString();
        parent = parent.substring(0,parent.length() - 1);
        return parent;
    }
    private static List<String> fillSubDirectories(String[] subDirectories, int index) {
        List<String> children = new ArrayList<>();
        for(int i = index; i < subDirectories.length; i++){
            children.add(subDirectories[i]);
        }
        return children;
    }
    private static void makeDir(String dirParent, String dirName, List<String> children){
        FolderObject folder = new FolderObject(dirParent,dirName,children);
        fileSystem.add(folder);
    }
}
