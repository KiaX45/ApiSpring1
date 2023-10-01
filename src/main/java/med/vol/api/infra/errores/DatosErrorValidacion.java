package med.vol.api.infra.errores;

import org.springframework.validation.FieldError;

public record DatosErrorValidacion(
        String mensaje,
        String error
) {
    public DatosErrorValidacion(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
