import java.io.Serializable;

public class Personal implements Serializable {

    private String name,father,mother,sid;
    private String DOB;
    private String adr;
    private String phn;

    public Personal(String name,String sid,String f,String m,String d,String adr,String phn) {

        this.name = name;
        this.sid=sid;
        this.father=f;
        this.mother=m;
        this.DOB=d;
        this.adr=adr;
        this.phn=phn;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "name='" + name + '\'' +
                ", father='" + father + '\'' +
                ", mother='" + mother + '\'' +
                ", DOB='" + DOB + '\'' +
                ", adr='" + adr + '\'' +
                ", phn='" + phn + '\'' +
                ", Student ID NO='" + sid + '\'' +
                '}';
    }
}
