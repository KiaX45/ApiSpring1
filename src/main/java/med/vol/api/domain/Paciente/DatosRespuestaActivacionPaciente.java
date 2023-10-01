package med.vol.api.domain.Paciente;

public record DatosRespuestaActivacionPaciente(
        String nombre,
        String email,
        Boolean activo
) {
    public DatosRespuestaActivacionPaciente(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getActivo());
    }
}
