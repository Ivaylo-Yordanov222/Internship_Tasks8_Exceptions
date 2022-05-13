import java.util.ArrayList;
import java.util.List;
public final class FileSystem {
    private static final String DIRECTORY_DOES_NOT_EXIST = "Directory does not exist";
    private static final String INVALID_NAME_SPECIFIED = "Invalid name specified";
    private static final String ILLEGAL_OPERATION = "Illegal operation";
    private static final List<FileSystemObject> fileSystem = new ArrayList<>();

    public static void makeFileHandler(String[] commandLine) {
        if(commandLine.length == 1){
            throw new CustomFileSystemException(INVALID_NAME_SPECIFIED);
        }
        String[] subDirectories = commandLine[1].split("/");
        String parentDirectoryName = getParent(subDirectories);

        FolderObject parentDirectory = findDirectory(parentDirectoryName);
        if(parentDirectory == null){
            throw new CustomFileSystemException(DIRECTORY_DOES_NOT_EXIST);
        }
        String fileName = subDirectories[subDirectories.length - 1];
        if(!Validator.isValidObjectName(fileName) || !Validator.isValidFileExtension(fileName) ){
            throw new CustomFileSystemException(INVALID_NAME_SPECIFIED);
        }

        FileObject file = new FileObject(parentDirectoryName,fileName);
        parentDirectory.getChildren().add(file);
        fileSystem.add(file);
    }
    public static void makeDirectoryHandler(String[] commandLine) {
        if(commandLine.length == 1){
            throw new CustomFileSystemException(INVALID_NAME_SPECIFIED);
        }
        String name = commandLine[1];
        String[] subDirectories = name.split("/");

        String parent;
        if(!Validator.isValidObjectName(name)){
            throw new CustomFileSystemException(INVALID_NAME_SPECIFIED);
        }
        List<FileSystemObject> children = new ArrayList<>();
        if(subDirectories.length == 1){
            parent = name;
            FolderObject fileSystemObject = new FolderObject(parent,name,children);
            fileSystem.add(fileSystemObject);
        }else{
            parent = getParent(subDirectories);
            if(Validator.isFile(parent)){
                throw new CustomFileSystemException(ILLEGAL_OPERATION);
            }
            FolderObject parentObject = findDirectory(parent);
            if(parentObject == null){
                throw new CustomFileSystemException(DIRECTORY_DOES_NOT_EXIST);
            }
            FileSystemObject child = new FolderObject(parent,name,children);
            parentObject.getChildren().add(child);
            fileSystem.add(child);
        }
    }
    private static FolderObject findDirectory(String directoryName){
        for(FileSystemObject dir : fileSystem){
            if(dir.getName().equals(directoryName)){
                if(dir instanceof FolderObject){
                    return (FolderObject) dir;
                }
            }
        }
        return null;
    }
    private static String getParent(String[] subDirectories){
        StringBuilder sb = new StringBuilder();
        String parent;
        for(int i = 0; i < subDirectories.length - 1; i++){
            sb.append(subDirectories[i]).append("/");
        }
        parent = sb.toString();
        return parent.substring(0,parent.length() - 1);
    }
}
