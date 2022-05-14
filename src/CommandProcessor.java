import java.util.Scanner;
public final class CommandProcessor {
    public static void commandHandler(Scanner sc){
        final String end = "END";
        String commandLine, command;
        String username, password, newPassword = null;
        while(!((commandLine = sc.nextLine()).trim().equals(end))){
            String[] splitCommand = commandLine.trim().split(" ");
            command = splitCommand[0].toLowerCase();

            username = splitCommand[1];
            password = splitCommand[2];
            if(splitCommand.length == 4){
                newPassword = splitCommand[3];
            }
            executeCommand(command,username,password,newPassword);
        }
        sc.close();
    }
    private static void executeCommand(String command, String username, String password, String newPassword) {
        Credentials currentCredentials = new Credentials(username,password);
        switch (command){
            case "enroll":
                System.out.println(command.toUpperCase() + " " + (currentCredentials.enroll()?"success":"fail"));
                break;
            case "auth":
                System.out.println(command.toUpperCase() + " " + (currentCredentials.auth()?"success":"fail"));
                break;
            case "chpass":
                try{
                    currentCredentials.changePassword(newPassword);
                }catch (OldPasswordConflictException e){
                    System.out.println(e.getMessage());
                }
                break;
        }
    }
}
