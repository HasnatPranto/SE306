import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Testimonial {

    Scanner sc=new Scanner(System.in);

    public void run() throws IOException, ClassNotFoundException {
        String name,sid;
        boolean cer=false;

        class1 cls=new class1();

        System.out.println("Enter name: ");
        name=sc.nextLine();
        System.out.println("Enter Registration Number: ");
        sid=sc.nextLine();

        boolean b=cls.search(name,sid);

        if(!b)
        {
            System.out.println("No Student found with this Registration Number");
            return;
        }
        else {
            Payment pay = new Payment();
            cer = pay.run();

            if(cer)
            {

            }
        }
    }
}
