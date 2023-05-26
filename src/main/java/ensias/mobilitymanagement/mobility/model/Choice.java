package ensias.mobilitymanagement.mobility.model;

import jakarta.persistence.*;

@Entity
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long choice_id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private MobilityType mobilityType;
    @Column(nullable = false, unique = true)
    private int priority;

    public Choice() {}

    public Choice(School school, Student student, MobilityType mobilityType, int priority) {
        this.school = school;
        this.student = student;
        this.mobilityType = mobilityType;
        this.priority = priority;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Long getChoice_id() {
        return choice_id;
    }

    public void setChoice_id(Long id) {
        this.choice_id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public MobilityType getMobilityType() {
        return mobilityType;
    }

    public void setMobilityType(MobilityType mobilityType) {
        this.mobilityType = mobilityType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
