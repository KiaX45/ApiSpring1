package med.vol.api.domain.usuarios;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuarios(
        @NotBlank
        String login,
        @NotBlank
        String clave
) {
}
