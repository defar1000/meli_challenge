package co.com.mercadolibre.api;

import co.com.mercadolibre.model.person.Person;
import co.com.mercadolibre.usecase.mutant.MutantUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.IllegalFormatFlagsException;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final MutantUseCase useCase;

    private final Logger logger = Logger.getLogger(MutantUseCase.class.getName());

    /**
     * Servicio POST para validar si una persona es mutante, recibe en el body un objeto con un array que
     * contiene el ADN a validar.
     * Ejemplo:
     *      {
     *          "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
     *      }
     * @param person Es un array de string que contiene el ADN
     * @return Restornara el objeto recibido con un parametro adicional que dira si es mutante o no
     * y devolvera un HTTP 200-OK en caso de ser mutante y un HTTP 403-Forbidden en caso contrario
     */
    @PostMapping(path = "/mutant")
    public ResponseEntity<Person> isMutant(@RequestBody Person person) {
        logger.info("Inicia validacion de mutante");
        Person result = useCase.validateMutant(person);
        if(result.isMutant()) {
            logger.info("Positivo para mutante");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        logger.info("Negativo para mutante");
        return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
    }

    /**
     * Servicio GET para obtener las estadisticas de las personas analizadas.
     * - Cantidad de mutantes
     * - Cantidad de humanos
     * - Relacion entre mutantes y humanos
     * @return Devuelve un Json con
     */
    @GetMapping(path = "/stats")
    public ResponseEntity<HashMap<String, String>> stats(){
        return new ResponseEntity<>(useCase.stats(), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalFormatFlagsException.class)
    public ResponseEntity<String> handleException(IllegalFormatFlagsException ex){
        return new ResponseEntity<>(ex.getFlags(), HttpStatus.BAD_REQUEST);
    }

}
