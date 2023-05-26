package ensias.mobilitymanagement.mobility.resource;

import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController dans le cas de ResponseEntity
@Controller
//@RequestMapping("/students")
public class StudentResource {
    private final StudentService studentService;
    @Autowired
    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Student>> getAllStudents() {
//        List<Student> students = studentService.findAllStudents();
//        return new ResponseEntity<>(students, HttpStatus.OK);
//    }

    @GetMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        return "students";
    }


//    @GetMapping("/find/{id}")
//    public ResponseEntity<Student> findStudentById(@PathVariable("id") Long id) {
//        Student student = studentService.findStudentById(id);
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }

//    @PostMapping("/add")
//    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
//        Student newStudent = studentService.addStudent(student);
//        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
//    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "addstudent";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }
//    @PutMapping("/update")
//    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
//        Student updatedStudent = studentService.updateStudent(student);
//        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
//    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);
        return "updatestudent";
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
//        studentService.deleteStudent(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
@Transactional

    @GetMapping("/delete/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";

    }
}
