package pl.edu.agh.to.school.course;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.to.school.student.Student;
import pl.edu.agh.to.school.student.StudentService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "courses")
public class CourseController {
    private CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Course> getCourse() {
        return courseRepository.findAll();
    }

    @GetMapping("/{courseId}")
    public List<Student> getStudentById(@PathVariable int courseId) {
        List<Course> courses = courseRepository.findAll();
        return courses.get(courseId).getStudents();
    }
}
