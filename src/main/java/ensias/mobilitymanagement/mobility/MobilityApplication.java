package ensias.mobilitymanagement.mobility;

import com.fasterxml.jackson.core.type.TypeReference;
import ensias.mobilitymanagement.mobility.model.Filiere;
import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.repo.FiliereRepo;
import ensias.mobilitymanagement.mobility.repo.SchoolRepo;
import ensias.mobilitymanagement.mobility.repo.StudentRepo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.ArrayList;

@SpringBootApplication
public class MobilityApplication implements CommandLineRunner {
	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private FiliereRepo filiereRepo;
	@Autowired
	private SchoolRepo schoolRepo;


	public static void main(String[] args) {
		SpringApplication.run(MobilityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String studentPath="/Excel/student_notes.xlsx";
		String schoolPath="/Excel/schools_info.xlsx";
		MappingClass.studentReading(studentPath,studentRepo,filiereRepo);
		MappingClass.schoolReading(schoolPath,schoolRepo);
	}
}