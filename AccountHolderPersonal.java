import java.io.Serializable;

public class AccountHolderPersonal implements Serializable {

    String name;
    String DOB;
    private String adr;
    private String phn;
    String accNum;

    public AccountHolderPersonal(String name, String dOB, String adr, String phn,String accNum) {

        this.accNum = accNum;
        DOB = dOB;
        this.adr = adr;
        this.phn = phn;
        this.name = name;
    }

    @Override

    public String toString() {
        return ("Name=" + this.name + "\nDOB=" + this.DOB + "\nadr=" + this.adr + "\nphn=" + this.phn + "\naccNum="
                + this.accNum+'\n');
    }
}
