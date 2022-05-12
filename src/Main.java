import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while(!((input = scanner.nextLine()).trim().equals("END"))){
            try{
                int number = Integer.parseInt(input);
                System.out.println("Number");
            }
            catch (NumberFormatException e){
                System.out.println("Not a number");
            }
        }
    }
}