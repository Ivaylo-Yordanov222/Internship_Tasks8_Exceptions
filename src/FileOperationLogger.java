public class FileOperationLogger {
    private static int logErrorRow;
    public static int getErrorLogRow(){
        return logErrorRow;
    }
    public static void increaseLogErrorRow(){
        logErrorRow++;
    }
}
