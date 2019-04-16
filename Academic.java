import java.io.Serializable;

public class Academic implements Serializable {

    private String name,sid,department,session,cgpa;

    public Academic(String name, String sid, String department, String session, String cgpa) {
        this.name = name;
        this.sid = sid;
        this.department = department;
        this.session = session;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "Academic{" +
                "name='" + name + '\'' +
                ", sid='" + sid + '\'' +
                ", department='" + department + '\'' +
                ", session='" + session + '\'' +
                ", cgpa='" + cgpa + '\'' +
                '}';
    }
}
