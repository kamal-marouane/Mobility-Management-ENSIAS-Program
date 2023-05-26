package ensias.mobilitymanagement.mobility;

import com.fasterxml.jackson.core.type.TypeReference;
import ensias.mobilitymanagement.mobility.model.Filiere;
import ensias.mobilitymanagement.mobility.model.School;
import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.repo.SchoolRepo;
import ensias.mobilitymanagement.mobility.repo.StudentRepo;
import ensias.mobilitymanagement.mobility.repo.FiliereRepo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

public class MappingClass {
    public static void studentReading(String path, StudentRepo studentRepo, FiliereRepo filiereRepo) throws IOException {
        InputStream inputStream = TypeReference.class.getResourceAsStream(path);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            String codeApogee = dataFormatter.formatCellValue(row.getCell(0));
            String lastname = dataFormatter.formatCellValue(row.getCell(1));
            String firstname = dataFormatter.formatCellValue(row.getCell(2));
            String filiereName = dataFormatter.formatCellValue(row.getCell(3));
            String email = dataFormatter.formatCellValue(row.getCell(4));
            String phone = dataFormatter.formatCellValue(row.getCell(5));
            double note1A = -1; // initialize the value
            double noteS3 = -1; // initialize the value
            Cell note1ACell = row.getCell(6);
            if (note1ACell != null) {
                note1A = note1ACell.getNumericCellValue();
            }
            Cell noteS3Cell = row.getCell(7);
            if (noteS3Cell != null) {
                noteS3 = noteS3Cell.getNumericCellValue();
            }

            Filiere filiere = filiereRepo.findByFiliereName(filiereName);
            if (filiere == null) {
                filiere = new Filiere(filiereName);
                filiere = filiereRepo.save(filiere);
            }
            Student student = studentRepo.findStudentByCodeApogee(codeApogee);
            if(student == null) {
                student = new Student(codeApogee, lastname, firstname, filiere, email, phone, note1A, noteS3,"false");
                student = studentRepo.save(student);
            }
        }

        workbook.close();
    }

    public static void schoolReading(String path, SchoolRepo schoolRepo) throws IOException {
        InputStream inputStream = TypeReference.class.getResourceAsStream(path);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            String schoolName = dataFormatter.formatCellValue(row.getCell(0));
            String country = dataFormatter.formatCellValue(row.getCell(1));
            String city = dataFormatter.formatCellValue(row.getCell(2));
            int nbr_dd = (int) row.getCell(3).getNumericCellValue();
            int nbr_ec = (int) row.getCell(4).getNumericCellValue();

            School school = new School(schoolName, country, city, nbr_dd, nbr_ec);
            school = schoolRepo.save(school);
        }

        workbook.close();

    }
}
