import java.util.Scanner;
public class CommandProcessor {
    public static void inputHandler(Scanner scanner){
        String input;

        while(!((input = scanner.nextLine()).trim().equals("END"))){
            String[] commandLine = input.trim().split(" ");
            processCommand(commandLine);
        }
        scanner.close();
    }
    private static void processCommand(String[] commandLine){
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
