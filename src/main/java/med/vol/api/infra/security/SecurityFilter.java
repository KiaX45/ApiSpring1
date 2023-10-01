package med.vol.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.vol.api.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @version 1
 * @author Luis Alejandro
 * @apiNote Lo que bacicamente hace esta clase es que cada que se realize una
 * petición a este pase primeramente por esta clase se autentique el usuario correctamente
 * y luego pueda acceder a los servicios que requiera el usuario
 */


@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //vamos a crear el metodo para el filtro para que cada vez que se haga una petición pase primero por esto
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("El filtro esta siendo llamado");

        //Primero tenemos que obtener el header de la petición y despues obtener el token
        String token = request.getHeader("Authorization");//ES SUPER IMPORTANTE PONER Authorization DE MANERA CORRECTA
        //Por defecto el header siempre contiene la palabra Bear pero lo que nosotros necesitamos solo es el token
        //por eso debemos quitarla pero que pasa si el header esta vacio o si la palabra no existe

        if(token!= null){
            token = token.replace("Bearer", "");
            token = token.trim();
            String subject = tokenService.getsubject(token);
            System.out.println(subject);
            if(subject != null){
                //token valido
                UserDetails usuario = usuarioRepository.findByLogin(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); //Forzamos ese inicio de seción
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        //vamos a llamar al otro filtro
        filterChain.doFilter(request,response);
        }
}
