package med.vol.api.controler;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired //no es recomendable hacer esto porque no se puede testear
    private MedicoRepository medicoRepository;

    //Creación de un metodo para haceptar la request de post desde un apartado diferente
    @PostMapping//Este metodo resuleve un Post
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosMedico, //esta dice que se prepare para recibir un body externo
            UriComponentsBuilder uriComponentsBuilder){ //Esta es para facilitar el enlace de la respuesta
            System.out.println("El request llega correctamente");

            System.out.println(datosMedico);
            Medico medico = medicoRepository.save(new Medico(datosMedico));

            //return 201 Created y la url donde se puede obtener el medico creado
            DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico);
            //creamos un objeto para enviar como respuesta para que el usuario sepa donde encontrar el objeto creado
            URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId_medico()).toUri(); //creamos un url con id dinamico
            return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> ListarMedicos(@PageableDefault(size = 2) Pageable paginacion){ //pagebladefault ponemos siertas opciones por default
        //List<Medico> medicos = new ArrayList<>();
       // medicos.stream().filter( (medico) -> medico.getEspecialidad().equals(Especialidad.ORTOPEDIA));
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map((medico) -> new DatosListadoMedico(medico)));
        //En este caso no utilizamos la propiedad stream porque lo que estamos usando no es el map de stream si no el
        //map que nos proporciona Spring a la hora de enviar un find all con un argumento
    }

    @PutMapping
    @Transactional//Jpa cuando termine este metodo se va a liberar
    public ResponseEntity actualizarMedico (@RequestBody @Valid DatosActualizarMedico datosActualizaMedico){
            Medico medico = medicoRepository.getReferenceById(datosActualizaMedico.id());
            medico.actualizarDatos(datosActualizaMedico);
            //Gracias a transaccional cuando termina esta función se hace un commit si fue
            //exitoso pero en caso contrario se realizara un rollback automaticamente
            return ResponseEntity.ok(new DatosRespuestaMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    //Traer un medico pero con un id definido
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity getMedicoByID(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico);
        return ResponseEntity.ok(datosRespuestaMedico);
    }



 /*   public void eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);

    }*/



}
