package med.vol.api.controler;

import med.vol.api.domain.Paciente.*;
import med.vol.api.domain.usuarios.DatosAutenticacionUsuarios;
import med.vol.api.domain.usuarios.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacientesControler {
    @Autowired //Proporciona una instancia del servicio en cuestión sin la necesidad de crearla por nuestra cuenta
    private PacienteRepository pacienteRepository;

    //Creación de un metodo para haceptar la request de post desde un apartado diferente
    @PostMapping//Este metodo resuleve un Post
    @Transactional
    public String registrarPaciente(@RequestBody @Valid DatosPaciente datosPaciente) { //esta dice que se prepare para recibir un body externo
        System.out.println("El request llega correctamente");
        System.out.println(datosPaciente);
        pacienteRepository.save(new Paciente(datosPaciente));
        return "todo correcto";
    }

    //Creamos el metodo para traer medicos por id
    @GetMapping("/{id}")
    @Transactional
    /**
     * Esta funcion nos permite retornar el id del paciente
     * @param el parametro tiene la anotación @PathVariable porque el id biene en el url por eso al poner esa anotación
     *           le hacemos entender a Spring que vamos a obtener el id de la url que envia el cliente
     * @Return retornamos un paciente junto con un codiho Http 200
     */

    public ResponseEntity<DatosRespuestaPaciente> obternerPacientePorID(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        DatosRespuestaPaciente respuestaPaciente = new DatosRespuestaPaciente(paciente);
        System.out.println("Los datos se enviaron correctamente");
        return new ResponseEntity<DatosRespuestaPaciente>(respuestaPaciente, HttpStatus.OK);
    }

    /**
     * @Function Vamos a crear el metodo para retornar todos los usuarios de la base de datos
     */
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaPaciente>> obtenerTodosLosUsuarios(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue(paginacion).map((paciente) -> new DatosRespuestaPaciente(paciente)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaActivacionPaciente> activaroDesactivarUsuario(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarActivarPaciente();
        DatosRespuestaActivacionPaciente datosRespuestaPaciente = new DatosRespuestaActivacionPaciente(paciente);
        return new ResponseEntity<DatosRespuestaActivacionPaciente>(datosRespuestaPaciente, HttpStatus.OK);
    }
}
