import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Credentials {
    private final static HashMap<String, ArrayList<String>> usersCredentials = new HashMap<>();
    private final String username;
    private String password;

    public Credentials(String username,String password)
    {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public boolean enroll(){
        if(!Credentials.usersCredentials.containsKey(this.getUsername())){
            Credentials.usersCredentials.put(this.getUsername(), new ArrayList<>(100));
            Credentials.usersCredentials.get(this.getUsername()).add(this.getPassword());
            return true;
        }
        return false;
    }
    public boolean auth(){
        if(Credentials.usersCredentials.containsKey(this.getUsername())){
            return Credentials.usersCredentials.get(this.getUsername())
                    .get(Credentials.usersCredentials.get(this.getUsername()).size()-1)
                    .equals(this.getPassword());
        }
        return false;
    }
    public void changePassword(String newPassword){
        if(Credentials.usersCredentials.containsKey(this.getUsername()) && Credentials.usersCredentials.get(this.getUsername())
                .get(Credentials.usersCredentials.get(this.getUsername()).size()-1)
                .equals(this.getPassword())){
            if(!Credentials.usersCredentials.get(this.getUsername()).contains(newPassword)){
                Credentials.usersCredentials.get(this.getUsername()).add(newPassword);
                this.setPassword(newPassword);
                System.out.println("CHPASS success");
            }
            else{
                List<String> oldCurrentUserPasswords = Credentials.usersCredentials.get(this.getUsername());
                throw new OldPasswordConflictException(oldCurrentUserPasswords, newPassword);
            }
        }else{
            System.out.println("CHPASS failed");
        }
    }
}
