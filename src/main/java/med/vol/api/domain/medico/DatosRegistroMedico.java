package med.vol.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.domain.direccion.DatosDireccion;


//Esta clase esta destinada a ser una plantilla para los datos que se enviaran
//Tambien es en esta clase que se deberan hacer las validaciones de los datos por
//medio del modulo validation
public record DatosRegistroMedico(
        @NotBlank(message = "El nombre del medico no puede estar vacio")
        String nombre,
        @NotBlank(message = "El email es un campo obligatorio")
        String email,
        @NotBlank
                @Pattern(regexp = "\\d{4,6}")
        String documento,

        @NotBlank
        String telefono,
        @NotNull
        Especialidad especialidad,
        @NotNull //Es en este caso null porque es un oibjeto
                @Valid //para validar que los datos esten bien
        DatosDireccion direccion) {


}
