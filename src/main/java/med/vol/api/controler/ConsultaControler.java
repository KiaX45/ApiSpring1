package med.vol.api.controler;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.domain.consultas.AgendaDeConsultasService;
import med.vol.api.domain.consultas.DatosAgendarConsulta;
import med.vol.api.domain.consultas.DatosDetalleConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaControler {

    @Autowired
    private AgendaDeConsultasService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleConsulta> agendar(@RequestBody @Valid DatosAgendarConsulta datos){
        service.agendar(datos);
        return new ResponseEntity<>(new DatosDetalleConsulta(datos), HttpStatus.OK);
    }
}
