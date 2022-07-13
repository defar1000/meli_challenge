package co.com.mercadolibre.api;

import co.com.mercadolibre.model.person.Person;
import co.com.mercadolibre.usecase.mutant.MutantUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final MutantUseCase useCase;


    @PostMapping(path = "/mutant")
    public boolean isMutant(@RequestBody Person person) {
      return useCase.isMutant(person);
    }

    @GetMapping(path = "/stats")
    public String stats(){
        return useCase.stats();
    }
}
