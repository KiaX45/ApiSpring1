package med.vol.api.domain.Paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.direccion.DatosDireccion;

//En esete apartado solo van a ir los datos que el servicio va a requerir
//Tambien se pondran validaciones vacicas de los datos entrantes
public record DatosPaciente(
        @NotBlank //para comprobar que no sea vacio pero tampoco nulo
        String nombre,
        @NotBlank
        String email,
        @NotBlank
        String documentoIdentidad,
        @NotBlank
        String telefono,
        @NotNull
                @Valid
        DatosDireccion direccion,

        @NotNull
        Boolean activo
) {
}


