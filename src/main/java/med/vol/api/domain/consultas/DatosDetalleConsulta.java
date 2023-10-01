package med.vol.api.domain.consultas;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(
        Long id,
        Long idPaciente,
        Long idMedico,
        LocalDateTime fecha
) {
    public DatosDetalleConsulta(DatosAgendarConsulta datosAgendarConsulta){
        this(datosAgendarConsulta.id(), datosAgendarConsulta.idPaciente(), datosAgendarConsulta.idMedico(), datosAgendarConsulta.fecha());
    }
}
