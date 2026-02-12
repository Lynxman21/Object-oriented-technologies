package pl.edu.agh.to.school.student;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to.school.grade.Grade;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "students")
public class StudentController {
    private StudentService studentService;
    public record GradeRequest(int courseId, int gradeValue) {}

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(@RequestParam(required = false) String indexNumber) {
        if (indexNumber == null) return studentService.getStudents();
        else {
            return studentService.getStudentByIndex(indexNumber);
        }
    }

    @GetMapping("/{studentId}/avg")
    public Double getAvg(@PathVariable int studentId) {
        return studentService.getAvgFromCourses(studentId);
    }


    @PostMapping("/{studentId}/addGrade")
    public void addGrade(@PathVariable int studentId, @RequestBody GradeRequest gradeRequest) {
        studentService.addGrade(studentId, gradeRequest.gradeValue);
    }
}
