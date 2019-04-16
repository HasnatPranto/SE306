import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class MyServer {
    private String name,DOB,adr,phn,accNum,acctyp,pass;
    double balance;

    private ArrayList<AccountHolderPersonal> listPB= new ArrayList<>();
    private ArrayList<AccountHolderBanking> listB= new ArrayList<>();
    Scanner sc= new Scanner(System.in);

    static ObjectOutputStream objectOutputStream;
    static ObjectInputStream objectInputStream;

    public void ini() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ObjectOutputStream objPers = new ObjectOutputStream(new FileOutputStream("./persBInfo.txt",true));
        ObjectOutputStream objAcad = new ObjectOutputStream(new FileOutputStream("./bankInfo.txt",true));

        File newFile= new File("./persBInfo.txt");
        File newAFile= new File("./bankInfo.txt");

        ObjectInputStream getPersBObj= new ObjectInputStream(new FileInputStream("./persBInfo.txt"));
        ObjectInputStream getBankObj= new ObjectInputStream(new FileInputStream("./bankInfo.txt"));

        if(newFile.length()==0){

            adding();
            showtime();
        }
        listPB= (ArrayList<AccountHolderPersonal>) getPersBObj.readObject();
        listB= (ArrayList<AccountHolderBanking>) getBankObj.readObject();

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        int req;
        MyServer mys= new MyServer();

        ServerSocket ss=new ServerSocket(3333);
        Socket socket= ss.accept();
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());

        System.out.println("Server OK");


        while(true) {

            System.out.println("1.Create a New Account\n2.Payment\n"
                    + "3.See the detailed list of clients\n4.Transection\n0.GB:)\n");

            req = (int) objectInputStream.readObject();

            mys.ini();

            switch (req) {
                case 1:
                    mys.adding();
                    break;

                case 2:
                    boolean b=false;
                    b=mys.payment();
                    if(b)
                    {
                        objectOutputStream.writeObject("Success");
                        objectOutputStream.close();objectInputStream.close();
                        ss.close();
                        socket.close();
                        exit(0);
                    }
                    break;

                case 3:
                    mys.showtime();
                    break;

                case 4:
                    mys.transection();
                    break;

                case 0:objectOutputStream.close();objectInputStream.close();
                    ss.close();
                    socket.close();
                    exit(0);
                    break;
            }

        }

    }

    public void adding() throws IOException, ClassNotFoundException {

        boolean df=false;
        //sc.nextLine();
        System.out.println("Enter name: ");
        name=(String) objectInputStream.readObject();
        System.out.println("Enter Date of Birth: ");
        DOB=(String) objectInputStream.readObject();
        System.out.println("Enter Address: ");
        adr=(String) objectInputStream.readObject();
        System.out.println("Enter Phone Number: ");
        phn=(String) objectInputStream.readObject();
        System.out.println("Enter Account Number: ");
        accNum=(String) objectInputStream.readObject();
        System.out.println("Enter Account Type: ");
        acctyp=(String) objectInputStream.readObject();
        System.out.println("Enter Account Balance: ");
        balance=(double) objectInputStream.readObject();

        while(true) {
            System.out.println("Enter password: ");
            pass = (String) objectInputStream.readObject();

            String passChec="^(?=.*[0-9A-Za-z)(?=.*[@#$%&^+=])(?=\\S).{8,15}$";


            Pattern pp= Pattern.compile(passChec);

            Matcher mm= pp.matcher(pass);

            if(mm.matches()) {
                System.out.println("Strong password!");
                break;
            }
                else
                System.out.println("Weak password\nTry with special characters, alphabets, numbers etc.");

        }
        AccountHolderPersonal acP= new AccountHolderPersonal(name,DOB,adr,phn,accNum);
        AccountHolderBanking acB= new AccountHolderBanking(accNum,acctyp,balance,pass);

        for(AccountHolderPersonal it: listPB)
        {
            if(it.accNum.equals(accNum))
            {
                for(AccountHolderBanking itr: listB)
                {
                    if(itr.accNum.equals(accNum))
                    {
                        System.out.println("An Accountholder has been found with this account number!\n");
                        System.out.print(it.toString());
                        System.out.print(itr.toString());
                        df=true;

                        System.out.println("What do you want?\n1.Replace it\t2.Leave\n");
                        int i= (int)objectInputStream.readObject();

                        switch(i)
                        {
                            case 1: listPB.remove(it);
                                listB.remove(itr);
                                listPB.add(acP);
                                listB.add(acB);
                                writer(listPB,listB);
                                break;

                            case 2: System.out.println("Okay\n");
                                break;

                            default: break;
                        }
                        break;
                    }
                }
                if(df)
                    break;
            }
        }
        if(!df)
        {
            listPB.add(acP);
            listB.add(acB);
            writer(listPB,listB);
        }
    }
    public void writer(ArrayList<AccountHolderPersonal> listP,ArrayList<AccountHolderBanking> listB) {

        FileOutputStream fout = null;
        ObjectOutputStream object_out_for_serializable = null;

        try {
            fout = new FileOutputStream("./persInfo.txt");
            object_out_for_serializable = new ObjectOutputStream(fout);

            object_out_for_serializable.writeObject(listP);
            object_out_for_serializable.flush();

            fout = null;
            object_out_for_serializable = null;

            fout = new FileOutputStream("./bankInfo.txt");
            object_out_for_serializable = new ObjectOutputStream(fout);

            object_out_for_serializable.writeObject(listB);
            object_out_for_serializable.flush();

            System.out.println("Entry added Successfully\n");
        }
        catch (FileNotFoundException ex) {
            System.out.println("FileOutputStream in " + ex.toString());
        } catch (IOException ex) {
            System.out.println("ObjectOutputStream in " + ex.toString());
        }
    }

    public void showtime() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        Iterator it= listPB.iterator();
        Iterator iti= listB.iterator();
        Iterator itr= listB.iterator();
        double d;

        while(it.hasNext())
        {
            d=((AccountHolderBanking) itr.next()).getBalance();
            System.out.println(it.next().toString()+ iti.next().toString()+"balance= "+d+'\n');
        }

    }

    public boolean payment() throws IOException, ClassNotFoundException {

        double amount;
        String rid;
        boolean sok=false,paym=false;

        System.out.println("1 Simp1e step for Money Transfer!");

        System.out.println("1.Give your Account ID: ");

        accNum=(String) objectInputStream.readObject();

        for(AccountHolderBanking itr: listB)
        {
            if(itr.accNum.equals(accNum))
            {
                sok=true;

                System.out.println("Payment in process(100 BDT):");


                if(itr.getBalance()<100)
                {
                    System.out.println("Sorry, Insufficient balance");

                }
                else {

                    itr.setBalance(itr.getBalance()-100);

                    writer(listPB,listB);
                    System.out.println("Payment Completed!");
                    paym=true;
                    return paym;
                    }
                }
            }
                if(!sok)
                {
                    System.out.println("Sorry, Invalid Bank Account");
                    return paym;
                }
                return paym;
    }



    public void transection() throws IOException, ClassNotFoundException {
        int sw;

        System.out.println("What do you want:\n1.Deposit into Your Account\n2.Withdraw from Your Account\n");
        sw=(int) objectInputStream.readObject();

        switch(sw)
        {
            case 1: depo();
                break;
            case 2:	withdraw();
                break;
            default :break;

        }
    }
    public void depo() throws IOException, ClassNotFoundException {
        double amo;
        boolean b=false;

        System.out.println("Enter Your Account number: ");
        accNum=(String) objectInputStream.readObject();;

        for(AccountHolderBanking it: listB)
        {
            if(accNum.equals(it.accNum)) {

                b=true;
                System.out.println("Enter Amount: ");
                amo=(double) objectInputStream.readObject();

                if(amo>0)
                {
                    it.setBalance(it.getBalance()+amo);
                    writer(listPB,listB);
                    System.out.println("BDT "+amo+" Successfully deposited in your Account");
                    return;
                }
                else
                {
                    System.out.println("Sorry, Non positive amount is not an actual amount! Please, Try Again.");
                    return;
                }
            }
        }
        if(!b)
            System.out.println("Sorry, Invalid Client ID!");
    }
    public void withdraw() throws IOException, ClassNotFoundException {
        double amo;
        boolean b=false;

        System.out.println("Enter Your Account number: ");
        accNum=(String) objectInputStream.readObject();

        for(AccountHolderBanking it: listB)
        {
            if(accNum.equals(it.accNum)) {

                b=true;
                System.out.println("Enter Amount: ");
                amo=(double) objectInputStream.readObject();

                if(amo<=0)
                {
                    System.out.println("Invalid Amount! Transection Terminated.");
                    return;
                }
                if(it.getBalance()-amo>=50)
                {
                    it.setBalance(it.getBalance()-amo);
                    writer(listPB,listB);
                    System.out.println("BDT "+amo+" Successfully withdrawn from your Account");
                    return;
                }
                else
                {
                    System.out.println("Sorry, if continued, your balance may not satisfy the minimum balance limit for an Account"
                            + "\nTransection Terminated.");
                    return;
                }
            }
        }
        if(!b)
            System.out.println("Sorry, Invalid Client ID!");
    }

}
