package co.com.mercadolibre.api;
import co.com.mercadolibre.usecase.mutant.MutantUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final MutantUseCase useCase;


    @GetMapping(path = "/mutant")
    public boolean isMutant() {
      return useCase.isMutant();
    }
}
