package co.com.mercadolibre.api;

import co.com.mercadolibre.model.person.Person;
import co.com.mercadolibre.usecase.mutant.MutantUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.IllegalFormatFlagsException;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final MutantUseCase useCase;

    private final Logger logger = Logger.getLogger(MutantUseCase.class.getName());

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

    @GetMapping(path = "/stats")
    public String stats(){
        return useCase.stats();
    }

    @ExceptionHandler(IllegalFormatFlagsException.class)
    public ResponseEntity<String> handleException(IllegalFormatFlagsException ex){
        return new ResponseEntity<>(ex.getFlags(), HttpStatus.BAD_REQUEST);
    }
}
