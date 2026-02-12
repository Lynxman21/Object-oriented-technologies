package pl.edu.agh.to.school.grade;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.student.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GradeBook {
    private final Map<String, List<Grade>> studentGrades = new HashMap<>();

    public GradeBook() {
    }

    public Map<String, List<Grade>> getStudentGrades() {
        return studentGrades;
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("[GradeBook] Service is working");
    }

    @PreDestroy
    public void Destroyed() {
        System.out.println("[GradeBook] Service is going to be destroyed");
    }

    // Fabryka 2.2
    public Grade assignGrade(Student student, Course course, double gradeValue) {
        Grade g = new Grade(course, gradeValue);
        studentGrades.computeIfAbsent(student.getIndexNumber(),s -> new ArrayList<>()).add(g);
        return g;
    }
}
