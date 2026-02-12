package pl.edu.agh.to.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.grade.GradeBook;
import pl.edu.agh.to.school.student.Student;
import pl.edu.agh.to.school.student.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class SchoolApplicationTests {
    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeBook gradeBook;

    @MockBean
    private SchoolInitializer schoolInitializer;

    @Test
    void giveGradeTest() {
        Student student = new Student(
                "Marcin",
                "Najman",
                LocalDate.of(1979, 3, 13),
                "2137666",
                "cesarz@example.com"
        );

        Course course = new Course("Fizyka");
        double gradeVal = 2.0;
        String key = student.getIndexNumber();
        studentService.assignGrade(student,course,gradeVal);
        Map<String,List<Grade>> grades = gradeBook.getStudentGrades();

        assertNotNull(grades, "Grades map should not be null");
        assertFalse(grades.isEmpty(), "Grades should not be empty");
        assertEquals(grades.get(key).size(), 1);
        assertEquals(grades.size(), 1);

        Grade grade = grades.get(key).getFirst();
        assertEquals(grade.value(), gradeVal, 0.01);
        assertEquals(grade.course().getName(), "Fizyka");
    }

//    @Test
//    void contextLoads() {
//    }


}
