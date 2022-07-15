package co.com.mercadolibre.api;

import co.com.mercadolibre.api.fakes.PersonFake;
import co.com.mercadolibre.model.person.Person;
import co.com.mercadolibre.model.person.gateways.PersonRepository;
import co.com.mercadolibre.usecase.mutant.MutantUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MutantServiceTest {

    @Mock
    private PersonRepository personRepository;
    private ApiRest api;

    @BeforeEach
    public void setup(){
        MutantUseCase useCase = new MutantUseCase(personRepository);
        api = new ApiRest(useCase);
    }

    @Test
    void validateMutant() {
        ResponseEntity<Person> result = api.isMutant(PersonFake.mutant());
        assertTrue(result.getBody().isMutant());
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void validateHuman() {
        ResponseEntity<Person> result = api.isMutant(PersonFake.human());
        assertFalse(result.getBody().isMutant());
        assertEquals(403, result.getStatusCodeValue());
    }

    @Test
    void stats() {
        Mockito.when(personRepository.getCountHumans()).thenReturn(50L);
        Mockito.when(personRepository.getCountMutants()).thenReturn(25L);
        ResponseEntity<HashMap<String, String>> result = api.stats();
        assertEquals(50L, Long.parseLong(result.getBody().get("count_human_dna")));
        assertEquals(25L, Long.parseLong(result.getBody().get("count_mutant_dna")));
        assertEquals(0.5, Float.parseFloat(result.getBody().get("ratio")));
        assertEquals(200, result.getStatusCodeValue());
    }
}