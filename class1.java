import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class class1 {

    private String name,fn,mn,DOB,adr,phn,sid,ses,cgpa,dep;

    private ArrayList<Personal> listP= new ArrayList<>();
    private ArrayList<Academic> listA= new ArrayList<>();
    Scanner sc= new Scanner(System.in);

    static ObjectOutputStream objectOutputStream;
    static ObjectInputStream objectInputStream;

    public void ini() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ObjectOutputStream objPers = new ObjectOutputStream(new FileOutputStream("./persInfo.txt",true));
        ObjectOutputStream objAcad = new ObjectOutputStream(new FileOutputStream("./acadInfo.txt",true));

        File newFile= new File("./persInfo.txt");
        File newAFile= new File("./acadInfo.txt");

        ObjectInputStream getPersObj= new ObjectInputStream(new FileInputStream("./persInfo.txt"));
        ObjectInputStream getAcadObj= new ObjectInputStream(new FileInputStream("./acadInfo.txt"));

        if(newFile.length()==0){

            adding();
        }
        listP= (ArrayList<Personal>) getPersObj.readObject();
        listA= (ArrayList<Academic>) getAcadObj.readObject();

    }

    public void adding() throws IOException, ClassNotFoundException {

        boolean df=false;
        //sc.nextLine();
        System.out.println("Enter name: ");
        name=sc.nextLine();
        System.out.println("Enter Registration Number: ");
        sid=sc.nextLine();
        System.out.println("Enter Father's name: ");
        fn=sc.nextLine();
        System.out.println("Enter Mother's name: ");
        mn=sc.nextLine();
        System.out.println("Enter Date of Birth: ");
        DOB=sc.nextLine();
        System.out.println("Enter Address: ");
        adr=sc.nextLine();
        System.out.println("Enter Phone Number: ");
        phn=sc.nextLine();
        System.out.println("Enter Department: ");
        dep=sc.nextLine();
        System.out.println("Enter Session: ");
        ses=sc.nextLine();
        System.out.println("Enter CGPA: ");
        cgpa=sc.nextLine();

        Personal acP= new Personal(name,sid,fn,mn,DOB,adr,phn);
        Academic acA= new Academic(name,sid,dep,ses,cgpa);

        for(Personal it: listP)
        {
            if(it.getSid().equals(sid))
            {
                for(Academic itr: listA)
                {
                    if(itr.getSid().equals(sid))
                    {
                        System.out.println("An Student has been found with this registration number!\n");
                        System.out.print(it.toString());
                        System.out.print(itr.toString());
                        df=true;

                        System.out.println("What do you want?\n1.Replace it\t2.Leave\n");
                        int i= sc.nextInt();

                        switch(i)
                        {
                            case 1: listP.remove(it);
                                listA.remove(itr);
                                listP.add(acP);
                                listA.add(acA);
                                writer(listP,listA);
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
            listP.add(acP);
            listA.add(acA);
            writer(listP,listA);
        }
    }
    public void writer(ArrayList<Personal> listP,ArrayList<Academic> listB) {

        FileOutputStream fout = null;
        ObjectOutputStream object_out_for_serializable = null;

        try {
            fout = new FileOutputStream("./persInfo.txt",true);
            object_out_for_serializable = new ObjectOutputStream(fout);

            object_out_for_serializable.writeObject(listP);
            object_out_for_serializable.flush();

            fout = null;
            object_out_for_serializable = null;

            fout = new FileOutputStream("./acadInfo.txt",true);
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
        Iterator it= listP.iterator();
        Iterator iti= listA.iterator();
        Iterator itr= listA.iterator();
        double d;

        while(it.hasNext() && iti.hasNext())
        {
            System.out.println(it.next().toString()+ iti.next().toString()+'\n');
        }

    }

    public boolean search(String name, String sid)
    {
        boolean df=false;

        for(Personal it: listP)
        {
            if(it.getSid().equals(sid))
            {
                for(Academic itr: listA)
                {
                    if(itr.getSid().equals(sid))
                    {
                        df=true;
                        return df;
                    }
                }
                if(df)
                    break;
            }
        }
        return df;

    }

    public void prepCertificate(String name,String sid) throws IOException {
        for(Personal it: listP)
        {
            if(it.getSid().equals(sid))
            {
                for(Academic itr: listA)
                {
                    if(itr.getSid().equals(sid))
                    {
                       File Certificate= new File("./Certificate.txt");

                        FileWriter writer = new FileWriter(Certificate);
                        writer.write(it.getName()+", Father: "+it.getFather()+", Mother: "+it.getMother()+", Session: "
                        + itr.getSession()+" has been a student of Hon's Program in"+ itr.getDepartment()+". His/her CGPA" +
                                " is "+itr.getCgpa()+". I wish him/her a succesful future.")
                        ;
                        writer.close();
                    }
                }

            }
        }

    }
    public void removeM() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        String s;
        boolean f=false;

        System.out.println("Enter the Account Number:\n");
        s=(String) objectInputStream.readObject();

        for(Personal it: listP)
        {
            if(it.getSid().equals(s))
            {
                for(Academic itr: listA)
                {
                    if(itr.getSid().equals(s))
                    {
                        f=true;
                        listP.remove(it);
                        listA.remove(itr);
                        writer(listP,listA);
                        break;
                    }
                }
                if(f)
                    break;
            }
        }
        if(f) System.out.println("Entry has been successfully deleted");
        else System.out.println("No Match found!");
    }

}
