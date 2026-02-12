package pl.edu.agh.to.school.student;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.grade.GradeService;

@Service
public class StudentService {
    private GradeService grade;

    public StudentService(GradeService grade) {
        this.grade = grade;
        System.out.println("Student Service has been created");
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("[Student] Service is working");
    }

    public void assignGrade(Student student, Course course, double gradeValue) {
        grade.assignGrade(student, course, gradeValue);
    }

    @PreDestroy
    public void Destroyed() {
        System.out.println("[Student] Service is going to be destroyed");
    }
}
