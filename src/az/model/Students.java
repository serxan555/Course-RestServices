package az.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "students")
public class Students {

    @XmlElement(name = "student")
    private List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
