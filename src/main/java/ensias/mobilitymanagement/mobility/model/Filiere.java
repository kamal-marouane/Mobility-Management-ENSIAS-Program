package ensias.mobilitymanagement.mobility.model;


import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long filiereId;
    @Column(nullable = false, unique = true)
    private String filiereName;


    public Filiere() {}

    public Filiere(Long id, String nom) {
        this.filiereId = id;
        this.filiereName = nom;
    }

    public Filiere(String name) {
        this.filiereName = name;

    }

    public Long getFiliereId() {
        return filiereId;
    }

    public void setFiliereId(Long id) {
        this.filiereId = id;
    }

    public String getFiliereName() {
        return filiereName;
    }

    public void setFiliereName(String name) {
        this.filiereName = name;
    }


}


