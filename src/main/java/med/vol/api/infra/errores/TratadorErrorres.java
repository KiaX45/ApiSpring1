package med.vol.api.infra.errores;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice//Identificamos a esta clase como una clase que tratara errores
public class TratadorErrorres {

    @ExceptionHandler(EntityNotFoundException.class) //le decimos que tipo de exepción se va a manejar
    public ResponseEntity<String> tratarError404(){
        String message = "No se a encontrado la entidad";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        List listaErrores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaErrores);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation() {
        String message = "El valor ingresado excede la longitud máxima permitida";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }


}
