package med.vol.api.domain.consultas;

import med.vol.api.domain.Paciente.Paciente;
import med.vol.api.domain.Paciente.PacienteRepository;
import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.medico.MedicoRepository;
import med.vol.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultasService {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    public void agendar(DatosAgendarConsulta datosAgendarConsulta){
        if(pacienteRepository.findById(datosAgendarConsulta.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("este id para el paciente no fue encontrado");
        }

        if(datosAgendarConsulta.idMedico() != null && medicoRepository.existsById(datosAgendarConsulta.id())){

        }

        Paciente paciente = pacienteRepository.getReferenceById(datosAgendarConsulta.idPaciente());
        Medico medico = seleccionarMedico(datosAgendarConsulta);
        
        Consulta consulta = new Consulta(null, medico, paciente, datosAgendarConsulta.fecha());
            consultaRepository.save(consulta);
    }
    //Repaso de cambio
    private Medico seleccionarMedico(DatosAgendarConsulta datosAgendarConsulta) {

         if(datosAgendarConsulta.idMedico() != null){
            return medicoRepository.getReferenceById(datosAgendarConsulta.idMedico());
        }
        if(datosAgendarConsulta.especialidad() == null){
            throw  new ValidacionDeIntegridad("Debe seleccionar una especialidad del medico");
        }
        PageRequest pageRequest = PageRequest.of(0, 1); // Primera página, 1 registro por página
        List<Medico> medicos = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datosAgendarConsulta.especialidad(),
                datosAgendarConsulta.fecha(), pageRequest);
        if(medicos.isEmpty()){
            throw new ValidacionDeIntegridad("No hay medicos disponibles");
        }
        return medicos.get(0);

    }
}
