package pl.edu.agh.to.school.course;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.school.student.Student;

import java.time.LocalDate;

@Configuration
public class CourseFactory {
    public CourseFactory() {
    }

    @Bean
    public Course computerNetworksCourse() {
        var student = new Student("Piotr", "Budynek", LocalDate.of(1990, 11, 7), "22334455", "budynek@student.agh.edu.pl");
        var course = new Course("Sieci komputerowe");
        course.enrollStudent(student);
        return course;
    }

    @Bean
    public Course objectOrientedProgramingCourse() {
        var student = new Student("Paweł", "Tir", LocalDate.of(1990, 11, 7), "22334465", "tir@student.agh.edu.pl");
        var course = new Course("Programowanie Obiektowe");
        course.enrollStudent(student);
        return course;
    }
}
