import java.util.Random;
import java.util.Scanner;
public class task1 
{
    public static void main(String a[])
    {
        int n;
        Scanner sc=new Scanner(System.in);
        System.out.println("Let's Start the number game...");
        
        Random random = new Random();
        int randomInt = random.nextInt(100);
       
        System.out.println("Choose any random number between 1 to 100");
        
        n=sc.nextInt();
        // System.out.println("you guessed a number : ");
        // System.out.println(n);
        if(n>randomInt)
        {
            System.out.println("Random number is : "+randomInt);
            System.out.println("your guessed number is greater than random number");
        }
        else if(n<randomInt)
        {
            System.out.println("Random number is : "+randomInt);
            System.out.println("your guessed number is smaller than random number");
        }
        else
        {
            System.out.println("Random number is : "+randomInt);
            System.out.println("Congratulations! your guessed number is correct");
            System.out.println("game ends..");
        } 
        sc.close();  
    }                    
}
