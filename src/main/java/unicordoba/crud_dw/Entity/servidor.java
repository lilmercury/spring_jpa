package unicordoba.crud_dw.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servidor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class servidor implements Serializable {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String ip;

    @Column(length = 100)
    private String nombre;

    @Column(length = 50)
    private String disco;

    @Column(length = 50)
    private String memoria;

    @Column(length = 50)
    private String procesador;

    @Column(length = 50)
    private String ubicacion;

    @Column(name = "sistema_operativo", length = 50)
    private String sistemaOperativo;

    @OneToMany(mappedBy = "server")
    @JsonManagedReference
    List<aplicacion> applications = new ArrayList<aplicacion>();
    
}
