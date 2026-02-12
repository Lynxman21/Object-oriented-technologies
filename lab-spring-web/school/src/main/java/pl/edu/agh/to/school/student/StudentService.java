package pl.edu.agh.to.school.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.grade.Grade;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
//        return List.of(new Student("Jan", "Kowalski", LocalDate.now(), "123456"));
        return studentRepository.findAll();
    }

    public List<Student> getStudentByIndex(String index) {
        return studentRepository.findStudentByIndexNumber(index);
    }

    public Student getStudentById(int id) {
        return studentRepository.findStudentById(id);
    }

    @Transactional
    public void addGrade(int studentId, int gradeValue) {
        Student student = getStudentById(studentId);
        Grade grade = new Grade(gradeValue);
        student.giveGrade(grade);
    }

    @Transactional
    public Double getAvgFromCourses(int studentId) {
        return studentRepository.getAvgFromCourse(studentId);
    }
}
