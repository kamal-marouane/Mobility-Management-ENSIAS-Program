package ensias.mobilitymanagement.mobility.resource;

import ensias.mobilitymanagement.mobility.model.School;
import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.service.SchoolService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController dans le cas de ResponseEntity
@Controller
@RequestMapping("/schools")
public class SchoolResource {
    @Autowired
    private final SchoolService schoolService;

    public SchoolResource(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/allschools")
    public ResponseEntity<List<School>> getAllSchools() {
        List<School> schools = schoolService.findAllSchools();
        return new ResponseEntity<>(schools, HttpStatus.OK);
    }
    @GetMapping("")
    public String getSchools(Model model) {
        List<School> schools = schoolService.findAllSchools();
        model.addAttribute("schools", schools);
    return "schools";
}
    @GetMapping("/{id}")
    public ResponseEntity<School> findSchoolById(@PathVariable("id") Long id) {
        School school = schoolService.findSchoolById(id);
        return new ResponseEntity<>(school, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        School newSchool = schoolService.addSchool(school);
        return new ResponseEntity<>(newSchool, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable("id") Long id, @RequestBody School school) {
        School updatedSchool = schoolService.updateSchool(school);
        return new ResponseEntity<>(updatedSchool, HttpStatus.OK);
    }
    @GetMapping("/add")
    public String addSchool(Model model) {
        School school = new School();
        model.addAttribute("school", school);
        return "addschool";
    }

    @PostMapping("/save")
    public String saveSchool(@ModelAttribute("school") School school) {
        schoolService.addSchool(school);
        return "redirect:/schools";
    }

    @GetMapping("/update/{id}")
    public String updateaSchool(@PathVariable(value = "id") long id, Model model) {
        School school = schoolService.findSchoolById(id);
        model.addAttribute("school", school);
        return "updateschool";
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchool(@PathVariable("id") Long id) {
        schoolService.deleteSchool(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteSchoolById(@PathVariable(value = "id") long id) {
        schoolService.deleteSchool(id);
        return "redirect:/schools";
    }
}
