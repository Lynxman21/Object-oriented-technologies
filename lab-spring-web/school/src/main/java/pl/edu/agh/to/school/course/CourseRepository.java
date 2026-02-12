package pl.edu.agh.to.school.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.school.student.Student;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
