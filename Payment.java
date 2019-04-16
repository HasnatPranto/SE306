import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Payment {

    public boolean run() throws IOException, ClassNotFoundException {

        Scanner sc= new Scanner(System.in);
        String s;
        boolean p=false;

        Socket clientSocket= new Socket("localhost",3333);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

        while(true) {

            if(sc.hasNextInt())
                objectOutputStream.writeObject(Integer.parseInt(sc.nextLine()));
            else if(sc.hasNextDouble())
                objectOutputStream.writeObject(Double.parseDouble(sc.nextLine()));
            else{
                //sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                objectOutputStream.writeObject(sc.nextLine());
            }

            s=(String)objectInputStream.readObject();

            if(s!=null && s.equals("Success"))
            {
                objectOutputStream.close();objectInputStream.close();
                clientSocket.close();
                break;
            }
        }
        if(s.equals("Success"))
        {
            p=true;
        }
        return p;
    }
}
