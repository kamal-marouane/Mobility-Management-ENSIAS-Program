package ensias.mobilitymanagement.mobility.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long studentId;
    @Column(nullable = false, updatable = false, unique = false)
    private String codeApogee;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String firstname;
    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
    private String email;
    private String phone;
    private double note1A=-1;
    private double noteS3=-1;
    private String eligible="false";

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Choice> choices;

    public Student() {}

    public Student(Long studentId, String firstname, String lastname,String codeApogee,String eligible) {
        this.codeApogee = codeApogee;
        this.lastname = lastname;
        this.firstname = firstname;
        this.studentId = studentId;
        this.eligible=eligible;
    }

    public Student(String codeApogee, String lastname, String firstname, Filiere filiere, String email, String phone, double note1A, double noteS3, String eligible) {
        this.codeApogee = codeApogee;
        this.lastname = lastname;
        this.firstname = firstname;
        this.filiere = filiere;
        this.email = email;
        this.phone = phone;
        this.note1A = note1A;
        this.noteS3 = noteS3;
        this.choices = choices;
        this.eligible=eligible;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long id) {
        this.studentId = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEligible() {
        return eligible;
    }

    public void setEligible(String eligible) {
        this.eligible = eligible;
    }

    public String getCodeApogee() {
        return codeApogee;
    }

    public void setCodeApogee(String codeApogee) {
        this.codeApogee = codeApogee;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public double getNote1A() {
        return note1A;
    }

    public void setNote1A(double note1A) {
        this.note1A = note1A;
    }

    public double getNoteS3() {
        return noteS3;
    }

    public void setNoteS3(double noteS3) {
        this.noteS3 = noteS3;
    }
}

