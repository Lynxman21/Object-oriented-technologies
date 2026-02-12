package pl.edu.agh.iisg.to.repository;

import pl.edu.agh.iisg.to.dao.CourseDao;
import pl.edu.agh.iisg.to.dao.StudentDao;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Student;

import java.util.*;

public class StudentRepository implements Repository<Student>{
    private StudentDao studentDao;
    private CourseDao courseDao;

    public StudentRepository(StudentDao studentDao,  CourseDao courseDao) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
    }

    @Override
    public Optional<Student> add(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Optional<Student> getById(int id) {
        return studentDao.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public void remove(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        Set<Course> courses = new HashSet<>(student.courseSet());
        for (Course c : courses) {
            c.studentSet().remove(student);
            student.courseSet().remove(c);
        }

        studentDao.remove(student);
    }

    public Optional<Student> getByIndex(int index) {
        return studentDao.findByIndexNumber(index);
    }

    public List<Student> findAllByCourseName(String courseName) {
        return courseDao.findByName(courseName)
                .map(course -> List.copyOf(course.studentSet()))
                .orElse(new ArrayList<>());
    }
}
