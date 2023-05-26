package ensias.mobilitymanagement.mobility;

import ensias.mobilitymanagement.mobility.repo.FiliereRepo;
import ensias.mobilitymanagement.mobility.repo.SchoolRepo;
import ensias.mobilitymanagement.mobility.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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