package ensias.mobilitymanagement.mobility.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class School implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long schoolId;
    @Column(nullable = false)
    private String schoolName;
    @Column(nullable = false)
    private String country;
    private String city;
    private int nbr_dd;
    private int nbr_ec;
    public School() {}

    public School(String name, String country, String city, int nbr_dd, int nbr_ec) {
        this.schoolName = name;
        this.country = country;
        this.city = city;
        this.nbr_dd = nbr_dd;
        this.nbr_ec = nbr_ec;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long id) {
        this.schoolId = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String name) {
        this.schoolName = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNbr_dd() {
        return nbr_dd;
    }

    public void setNbr_dd(int nbr_dd) {
        this.nbr_dd = nbr_dd;
    }

    public int getNbr_ec() {
        return nbr_ec;
    }

    public void setNbr_ec(int nbr_ec) {
        this.nbr_ec = nbr_ec;
    }
}
