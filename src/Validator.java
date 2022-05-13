public final class Validator {
    public static boolean isValidObjectName(String name){
        return (validateName(name) && validateNameStartsWithSpecialChars(name));
    }
    public static boolean isValidFileExtension(String filename){
        String[] splitFile = filename.split("\\.");
        String extension = splitFile[1];
        return extension.length() <= 6;
    }
    public static boolean isFile(String name){
        return name.contains(".");
    }
    private static char[] convertStringToCharArray(String str){
        char[] chars = new char[str.length()];
        for (int i = 0; i < chars.length; i++){
            chars[i] = str.charAt(i);
        }
        return chars;
    }
    private static boolean validateName(String name){

        char[] nameChars = convertStringToCharArray(name);
        boolean result = false;
        for (char currentChar : nameChars) {
            if (currentChar == '(' || currentChar == ')' || currentChar == '_' || currentChar == '!' || currentChar == '&' || currentChar == '/' || currentChar == '.'
                    || (currentChar > 47 && currentChar < 58)
                    || (currentChar > 64 && currentChar < 91)
                    || (currentChar > 96 && currentChar < 123)) {
                result = true;
            } else {
                return false;
            }
        }
        return result;
    }
    private static boolean validateNameStartsWithSpecialChars(String name){
        String[] splitDirectory = name.split("/");
        for (String currentFolder : splitDirectory) {
            if (currentFolder.startsWith("(") || currentFolder.startsWith(")")
                    || currentFolder.startsWith("_") || currentFolder.startsWith("!")
                    || currentFolder.startsWith(".") || currentFolder.startsWith("&")) {
                return false;
            }
        }
        return true;
    }
}
