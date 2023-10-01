package med.vol.api.domain.medico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.domain.direccion.Direccion;

@Entity (name = "medicos")
@Table (name = "medicos")
//vamos a utilizar LOmmbock para generar las cosas basicas de la calase
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_medico") //usa el parametro id para comparar los medicos
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_medico;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private Boolean activo;
    private  Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosMedico) {
        this.activo = true;
        this.nombre = datosMedico.nombre();
        this.email = datosMedico.email();
        this.documento = datosMedico.documento();
        this.telefono = datosMedico.telefono();
        this.especialidad = datosMedico.especialidad();
        this.direccion = new Direccion(datosMedico.direccion());
    }

    //Metodo para actualizar los datos del medico

    public void actualizarDatos(DatosActualizarMedico datosListadoMedico){
        if(datosListadoMedico.nombre() != null){
            this.nombre = datosListadoMedico.nombre();
        }

        if(datosListadoMedico.documento() != null){
            this.documento = datosListadoMedico.documento();
        }

        if(datosListadoMedico.direccion() != null){
            this.direccion = direccion.actualizarDatos(datosListadoMedico.direccion());
        }

    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
