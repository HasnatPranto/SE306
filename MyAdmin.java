import java.io.IOException;
import java.util.Scanner;

public class MyAdmin {

    public void run() throws IOException, ClassNotFoundException {

        String adpass;

        System.out.println("Enter Password");
        Scanner sc=new Scanner(System.in);

        adpass=sc.next();

        if(adpass.equals("iit123"))
        {
            class1 cl=new class1();

            while(true)
            {
                int s;
                System.out.println("1.Add A Student\n2.Remove A Student\n3.Exit");

                s=sc.nextInt();

                switch (s)
                {
                    case 1: cl.adding();
                            break;
                    case 2:cl.removeM();
                            break;
                    case 3: break;
                }
            }

        }

    }
}
