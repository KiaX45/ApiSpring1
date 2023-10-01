package med.vol.api.domain.medico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRespuestaMedico(
        @NotNull
        Long id,
        @NotBlank
        String nombre
) {
    public DatosRespuestaMedico(Medico medico) {
        this(medico.getId_medico(), medico.getNombre());
    }
}
