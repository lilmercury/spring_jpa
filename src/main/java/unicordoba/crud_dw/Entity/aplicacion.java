package unicordoba.crud_dw.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "aplicacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class aplicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String version;

    @ManyToOne()
    @JoinColumn(name = "server", nullable = false)
    @JsonBackReference
    servidor server;

    //aplicacion(servidor server) {
    //    super();
    //    this.server = server;
    //}
}