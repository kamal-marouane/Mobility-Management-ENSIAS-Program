package ensias.mobilitymanagement.mobility.service;

import ensias.mobilitymanagement.mobility.exception.StudentNotFoundException;
import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepo studentRepository;

    @Autowired
    public StudentService(StudentRepo studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student) {

            return studentRepository.save(student);

    }


    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudentById(Long id) {
        return studentRepository.findStudentByStudentId(id)
                .orElseThrow(() -> new StudentNotFoundException("Student by id " + id + " was not found"));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteStudentByStudentId(id);
    }

}

