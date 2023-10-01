package med.vol.api.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException{
    public ValidacionDeIntegridad(String error){
        super(error);
    }
}
