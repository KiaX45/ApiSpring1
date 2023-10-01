package med.vol.api.domain.Paciente;

public record DatosRespuestaPaciente(
        String nombre,
        String email
) {
    public DatosRespuestaPaciente(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail());
    }
}
