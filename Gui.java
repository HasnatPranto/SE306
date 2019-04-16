import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Gui {

    public static void main(String [] args) throws IOException, ClassNotFoundException {
        int sw;
        class1 cls=new class1();

        while(true) {

            cls.ini();
            cls.showtime();
            System.out.println("1.Apply For a testimonial\n2.Admin\n3.Student List\n4.Exit");

            Scanner sc = new Scanner(System.in);

            sw = sc.nextInt();

            switch (sw) {
                case 1:
                    Testimonial test = new Testimonial();
                    test.run();
                    break;

                case 2:
                    MyAdmin ad= new MyAdmin();
                    ad.run();
                    break;

                    case 3:
                    cls.showtime();
                    break;

                    case 4: return;

            }
        }
    }
}
