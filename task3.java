import java.util.Scanner;;
public class task3 
{
    public static void main(String a[])
    {
        int w,b,d;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your account's balance: ");
        b=sc.nextInt();
        System.out.println("Your current balance is "+b);
        System.out.println();

        System.out.println("Enter withdrawal amount: ");
        w=sc.nextInt();
        if(w>b)
        {
            System.out.println("not sufficient balance, withdrawal failed");
        }
        else{
            System.out.println("Amount withdrew successfully");
            System.out.println();     
        }
        System.out.println("Current balance :");
        int newB=b-w;
        System.out.println(newB);
        System.out.println("Enter amount to deposit :");
        d=sc.nextInt();
        int nb=newB+d;
        System.out.println("Current balance :");
        System.out.println(nb);

        System.out.println("transaction completed");

        sc.close();  
    }
}
