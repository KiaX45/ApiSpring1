package med.vol.api.domain.consultas;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(
        Long id,
        @NotNull
                @JsonAlias("id_paciente") //aqui le agregamos otra posibilidad para que los body Json no se confundan con los nombres
        Long idPaciente,
                @JsonAlias("id_medico")
        Long idMedico,
        @NotNull
                @Future //para que solo hacepte fechas en el futuro
        LocalDateTime fecha,

        Especialidad especialidad
) {
}
