package pl.edu.agh.to.school.notification;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.student.Student;

@Service
public interface NotificationService {
    public void notify(Student student,  Grade grade);
}
