package med.vol.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.vol.api.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //traemos el secreto
    @Value("${api.security.secret}") //Extrae el valor sel secret
    private String apiSecret;

    public String genereToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            System.out.println(algorithm);
            System.out.println(usuario);
            return  JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuario.getLogin()) //le damos el nombre de usuario
                    .withClaim("id", usuario.getId()) //le damos el id del usuario
                    .withExpiresAt(generarFechaVencimiento()) //que el token expire en cieto momento
                    .sign(algorithm);
        } catch (JWTCreationException exception){
           throw new RuntimeException();
        }

    }
    private Instant generarFechaVencimiento(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    //Metodo
    public String getsubject(String token){
        if(token == null){
            throw new RuntimeException("No llega el token");
        }
        System.out.println("Si llega aui por lo menos");
        System.out.println(token);
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);
            System.out.println(verifier);
            System.out.println(verifier.getSubject());
        } catch (JWTVerificationException exception){
            return exception.getMessage() + " " + exception.getLocalizedMessage();

        }

        return verifier.getSubject();
    }




}
