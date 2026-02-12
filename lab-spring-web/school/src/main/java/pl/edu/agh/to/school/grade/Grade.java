package pl.edu.agh.to.school.grade;

import jakarta.persistence.*;
import pl.edu.agh.to.school.student.Student;

@Entity
public class Grade {
    @Id
    @GeneratedValue
    private int id;

    private int gradeValue;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    public Grade(int value) {
        this.gradeValue = value;
    }

    public Grade() {
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public int getGradeValue() {
        return gradeValue;
    }
}
