public final class Validator {
    public static boolean validateDirectoryName(String name){

        char[] nameChars = convertStringToCharArray(name);
        boolean result = false;
        for (int i = 0; i < nameChars.length; i++){
            char currentChar = nameChars[i];
            if(currentChar == '(' || currentChar == ')' || currentChar == '_' || currentChar == '!' || currentChar == '.' || currentChar == '&' || currentChar == '/'
            || (currentChar > 47 && currentChar < 58)
            || (currentChar > 64 && currentChar < 91)
            || (currentChar > 96 && currentChar < 123)){
                result = true;
            }
            else{
                return false;
            }
        }
        return result;
    }
    public static boolean validateNameStartsWithSpecialChars(String name){
        String[] splitDirectory = name.split("/");
        for(int i = 0; i < splitDirectory.length; i++){
            String currentFolder = splitDirectory[i];
            if(currentFolder.startsWith("(") || currentFolder.startsWith(")")
            || currentFolder.startsWith("_") || currentFolder.startsWith("!")
            || currentFolder.startsWith(".") || currentFolder.startsWith("&")){
                return false;
            }
        }
        return true;
    }
    public static boolean checkFileExtension(String filename){
        String[] splitFile = filename.split("\\.");
        String extension = splitFile[1];
        if(extension.length() > 6) {
            return false;
        }
        return true;
    }
    public static boolean checkIsFile(String filename){
        String[] splitFile = filename.split("\\.");
        if(splitFile.length == 1){
            //is not a file
            return false;
        }
        return true;
    }
    private static char[] convertStringToCharArray(String str){
        char[] chars = new char[str.length()];
        for (int i = 0; i < chars.length; i++){
            chars[i] = str.charAt(i);
        }
        return chars;
    }
    public static boolean checkDirectoryExists(String directoryName){
        for (FileSystemObject fo : FileSystem.getFileSystem()){
            if(fo.getName().equals(directoryName))
            {
                return true;
            }
        }
         return false;
    }
}
