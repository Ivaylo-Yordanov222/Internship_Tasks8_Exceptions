import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while(!((input = scanner.nextLine()).trim().equals("END"))){
            String[] commandLine = input.trim().split(" ");
            processCommand(commandLine);
        }
        List<FileSystemObject> fileSystemObjects = FileSystem.getFileSystem();
        System.out.println(fileSystemObjects);
    }
    public static void processCommand(String[] commandLine){
        String command = commandLine[0];
        FileOperationLogger.increaseLogErrorRow();
        switch (command){
            case "mkdir":
                try{
                    FileSystem.makeDirectoryHandler(commandLine);
                }
                catch (CustomFileSystemException e){
                    System.out.println(FileOperationLogger.getErrorLogRow() + " - " + e.getMessage());
                }
                break;
            case "touch":
                try{
                    FileSystem.makeFileHandler(commandLine);
                }
                catch (CustomFileSystemException e){
                    System.out.println(FileOperationLogger.getErrorLogRow() + " - " + e.getMessage());
                }
        }
    }


}