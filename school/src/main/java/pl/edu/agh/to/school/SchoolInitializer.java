package pl.edu.agh.to.school;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.grade.GradeService;
import pl.edu.agh.to.school.student.Student;
import pl.edu.agh.to.school.student.StudentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchoolInitializer {
    private GradeService gradeServicce;
    private StudentService studentService;
    private Course computerNetworksCourse;
    private Course objectOrientedProgramingCourse;
    private List<Course> courses;

    @Value("${school.app.version}") String version;

    public SchoolInitializer(GradeService gradeServicce, StudentService studentService, Course computerNetworksCourse, Course objectOrientedProgramingCourse, List<Course> courses) {
        this.gradeServicce = gradeServicce;
        this.studentService = studentService;
        this.computerNetworksCourse = computerNetworksCourse;
        this.objectOrientedProgramingCourse = objectOrientedProgramingCourse;
        this.courses = courses;
    }

    @PostConstruct
    public void init() {
        System.out.println("Version: " + version);
        Student student = new Student(
                "Jan",
                "Kowalski",
                LocalDate.of(2000,1,1),
                "123456",
                "email@example.pl");

        Course course = new Course("ASD");
        course.enrollStudent(student);
        gradeServicce.assignGrade(student, course, 4.0);
    }

    @PostConstruct
    public void initComputerNetworksCourse() {
        computerNetworksCourse.getStudents()
                .forEach(student -> studentService.assignGrade(student, computerNetworksCourse, 4.5));
    }

    @PostConstruct
    public void computerNetworksCourse() {
        objectOrientedProgramingCourse.getStudents()
                .forEach(student -> studentService.assignGrade(student, objectOrientedProgramingCourse, 3.5));
    }

    @PostConstruct
    public void addAllStudentsGrades() {
        for (Course course : courses) {
            course.getStudents()
                    .forEach(student -> studentService.assignGrade(student, course, 3.0));
        }
    }
}
