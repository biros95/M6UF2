package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tabla clientes
 *
 * @author MarcosPortatil
 */
@Entity

//Creamos las consultas, la primera busca cliente por nombre
@NamedQueries({
@NamedQuery(name = "buscarClientePorNombre", query = "SELECT c FROM Client c WHERE c.nom=:nom"),
//Esta otra consulta busca los vehículos de cada cliente
@NamedQuery(name="cochesCliente", query="SELECT v FROM Vehicle v WHERE v.propietari.id=:id"), 
})
@Table(name = "Clientes")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clientId", unique = true)
    private long id;

    @Column(name = "nif", length = 9, nullable = false, unique = true)
    private String nif;

    @Column(name = "nomClient", length = 50)
    private String nom;
    
    
    //Relacion oneToMany, un cliente puede tener varios vehículos pero un vehículo solo puede
    //estar alquilado por un cliente.
    @OneToMany(mappedBy = "propietari")
    private List<Vehicle> listaVechiles;
    
    
    //Lo mismo que con las polizas
    @OneToMany(mappedBy = "cliente")
    private List<Polissa> listaPolizas;
    //Taula imbrica Adreca esta dins de Client
    
    //Añadimos el atributo de direccion como embedded
    @Embedded
    private Adreca adreca;

    public Client() {
    }

    public Client(long id, String nif, String nom, Adreca adreca) {
        this.id = id;
        this.nif = nif;
        this.nom = nom;
        this.adreca = adreca;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Vehicle> getListaVechiles() {
        return listaVechiles;
    }

    public void setListaVechiles(List<Vehicle> listaVechiles) {
        this.listaVechiles = listaVechiles;
    }

    public List<Polissa> getListaPolizas() {
        return listaPolizas;
    }

    public void setListaPolizas(List<Polissa> listaPolizas) {
        this.listaPolizas = listaPolizas;
    }

    public Adreca getAdreca() {
        return adreca;
    }

    public void setAdreca(Adreca adreca) {
        this.adreca = adreca;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nif=" + nif + ", nom=" + nom + ", adreca=" + adreca + '}';
    }

}
