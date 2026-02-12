package pl.edu.agh.to.school.grade;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.notification.NotificationService;
import pl.edu.agh.to.school.student.Student;

@Service
public class GradeService {
    private GradeBook gb;
    private NotificationService ns;

    public GradeService(GradeBook gb, NotificationService ns) {
        this.gb = gb;
        this.ns = ns;
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("[Grade] Service is working");
    }

    @PreDestroy
    public void Destroyed() {
        System.out.println("[Grade] Service is going to be destroyed");
    }

    public void assignGrade(Student student, Course course, double gradeValue) {
        Grade g = gb.assignGrade(student, course, gradeValue);
        ns.notify(student, g);
    }
}
