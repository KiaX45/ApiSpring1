package med.vol.api.domain.Paciente;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.domain.direccion.Direccion;

@Entity (name = "pacientes")
@Table (name = "pacientes")
//vamos a utilizar LOmmbock para generar las cosas basicas de la calase
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id_paciente;

    private String nombre;
    private String email;
    @Column(name = "documento_identidad")
    private String documentoIdentidad;
    private String telefono;
    private Boolean activo;
    @Embedded //lo que hace es tomar todos los atributos de la clase en cuestión y ponerlos como columnas en esta entidad
    private Direccion direccion;

    //creamos un contructor de la clase que recibira por parametro un objeto de tipo Datospaciente


    public Paciente(DatosPaciente datosPaciente) {
        this.nombre = datosPaciente.nombre();
        this.email = datosPaciente.email();
        this.documentoIdentidad = datosPaciente.documentoIdentidad();
        this.telefono = datosPaciente.telefono();
        this.direccion = new Direccion(datosPaciente.direccion());
    }

    public String desactivarActivarPaciente(){
        if(this.activo == null){
            this.activo = true;
            return "Activado";
        }
        this.activo = !activo;
        if(Boolean.TRUE.equals(this.activo)){
            return "paciente desactivado";
        }else {
            return "paciente activado";
        }
    }

}
