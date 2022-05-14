import java.util.List;
public class OldPasswordConflictException extends IllegalStateException{
    private final List<String> oldPasswords;
    private final String newPassword;
    public OldPasswordConflictException(List<String> oldPasswords,String newPassword){
        this.oldPasswords = oldPasswords;
        this.newPassword = newPassword;
    }
    private int getPasswordConflictIndex(){
        int i = 0;
        for (String oldPass : oldPasswords){
            if(oldPass.equals(newPassword)){
                break;
            }
            i++;
        }
        return (oldPasswords.size()-1) - i;
    }
    @Override
    public String getMessage() {
        return "CHPASS failed [Password matches a recently used one: " + getPasswordConflictIndex()+ "]";
    }
}
