package med.vol.api.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello") //Creación del endpoint
public class HelloControl {

        @GetMapping
        public String helloWorld(){
            return "Hello World";
        }
}
