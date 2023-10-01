package med.vol.api.controler;

import jakarta.validation.Valid;
import med.vol.api.domain.usuarios.DatosAutenticacionUsuarios;
import med.vol.api.domain.usuarios.Usuario;
import med.vol.api.infra.security.DatosJWTToken;
import med.vol.api.infra.security.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionControler {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarusuario(@RequestBody @Valid DatosAutenticacionUsuarios datosAutenticacionUsuarios){
        Authentication Autenticationtoken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuarios.login(),
                datosAutenticacionUsuarios.clave());
        System.out.println(Autenticationtoken);
        Authentication usuarioAutenticado = authenticationManager.authenticate(Autenticationtoken); //autenticamos el usuario con los datos dados por el mismo usuario
        String JWTtoken = tokenService.genereToken((Usuario) usuarioAutenticado.getPrincipal()); //generamos un token en formato de jwt
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
