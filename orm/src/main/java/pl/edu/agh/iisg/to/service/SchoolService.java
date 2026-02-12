package pl.edu.agh.iisg.to.service;

import pl.edu.agh.iisg.to.dao.CourseDao;
import pl.edu.agh.iisg.to.dao.GradeDao;
import pl.edu.agh.iisg.to.dao.StudentDao;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;
import pl.edu.agh.iisg.to.repository.StudentRepository;
import pl.edu.agh.iisg.to.session.TransactionService;

import java.util.*;

public class SchoolService {

    private final TransactionService transactionService;

    private final StudentDao studentDao;

    private final CourseDao courseDao;

    private final GradeDao gradeDao;

    private final StudentRepository studentRepository;

    public SchoolService(TransactionService transactionService, StudentDao studentDao, CourseDao courseDao, GradeDao gradeDao, StudentRepository studentRepository) {
        this.transactionService = transactionService;
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.gradeDao = gradeDao;
        this.studentRepository = studentRepository;
    }

    public boolean enrollStudent(final Course course, final Student student) {
        // TODO - implement
        Optional<Boolean> res = transactionService.doAsTransaction(() -> {
            if (student.courseSet().contains(course) || course.studentSet().contains(student)) {
                return false;
            }
            student.courseSet().add(course);
            course.studentSet().add(student);
            return true;
        });

        return res.isPresent() ? res.get() : false;
    }

    public boolean removeStudent(int indexNumber) {
        // TODO - implement
        Optional<Boolean> res = transactionService.doAsTransaction(() -> {
//            Optional<Student> studentOpt = studentDao.findByIndexNumber(indexNumber);
//            if (!studentOpt.isPresent()) return false;
//            Student student = studentOpt.get();
//            Set<Course> courses = new HashSet<>(student.courseSet());
//            for (Course c : courses) {
//                c.studentSet().remove(student);
//                student.courseSet().remove(c);
//            }
//            studentDao.remove(student);
//            return true;

            Optional<Student> studentOptional = studentRepository.getByIndex(indexNumber);
            if (!studentOptional.isPresent()) {
                return false;
            }
            studentRepository.remove(studentOptional.get());
            return true;
        });

        return res.isPresent() ? res.get() : false;
    }

    public boolean gradeStudent(final Student student, final Course course, final float gradeValue) {
        // TODO - implement
        Optional<Boolean> res = transactionService.doAsTransaction(() -> {
            if (gradeValue < 2.f ||  gradeValue > 5.f) {
                return false;
            }

            Grade grade = new  Grade(student, course, gradeValue);
            gradeDao.save(grade);
            student.gradeSet().add(grade);
            course.gradeSet().add(grade);
            return true;
        });

        return res.isPresent() ? res.get() : false;
    }

    public Map<String, List<Float>> getStudentGrades(String courseName) {
        // TODO - implement
        Optional<Map<String, List<Float>>> mapOpt = transactionService.doAsTransaction(() -> {
            Optional<Course> courseOpt = courseDao.findByName(courseName);
            if (!courseOpt.isPresent()) return Collections.emptyMap();

            Course course = courseOpt.get();
            Map<String, List<Float>> studentGrades = new HashMap<>();
            course.gradeSet().forEach(g -> {
                String name = g.student().firstName() + " " + g.student().lastName();
                if (studentGrades.containsKey(name)) studentGrades.get(name).add(g.grade());
                else studentGrades.put(name, new ArrayList<>(Arrays.asList(g.grade())));
            });
            return studentGrades;
        });

        return mapOpt.isPresent() ? mapOpt.get() : Collections.emptyMap();
    }
}
