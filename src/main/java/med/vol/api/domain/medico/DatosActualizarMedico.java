package med.vol.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.direccion.DatosDireccion;

public record DatosActualizarMedico(
        @NotNull // no Notblank porque es un long
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion
) {

}
