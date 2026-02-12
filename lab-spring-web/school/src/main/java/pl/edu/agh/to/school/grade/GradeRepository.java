package pl.edu.agh.to.school.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.school.student.Student;

@Repository
public interface GradeRepository  extends JpaRepository<Grade, Integer> {
}
