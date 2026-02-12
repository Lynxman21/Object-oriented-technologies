package pl.edu.agh.to.school.notification;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.student.Student;

@Service
@Profile("test")
public class ConsoleNotification implements NotificationService{
    public ConsoleNotification() {
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("[ConsoleNot] Service is working");
    }

    @PreDestroy
    public void Destroyed() {
        System.out.println("[ConsoleNot] Service is going to be destroyed");
    }

    @Override
    public void notify(Student student, Grade grade) {
        System.out.println("Student "+student.getFullName()+" got " + grade.toString());
    }
}
