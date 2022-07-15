package co.com.mercadolibre.usecase.mutant;

import co.com.mercadolibre.model.person.Person;
import co.com.mercadolibre.model.person.gateways.PersonRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@AllArgsConstructor
public class MutantUseCase {

    private final PersonRepository personRepository;

    /**
     * Recibe un objeto {@link Person} y hace el llamo al metodo isMutant(dna). Luego guarda el objeto {@link Person}
     * en la base de datos con el tributo mutant para saber si es mutante o no.
     * @param person Es el objero que contiene el ADN que se va a validar
     * @return Devuelve el objeto {@link Person} recibido con el tratributo mutant guardado
     */
    public Person validateMutant(Person person){
        person.setMutant(isMutant(person.getDna()));
        personRepository.savePerson(person);
        return person;
    }

    /**
     * Hace la validación de un ADN para saber si es un mutante, recibiendo como parametro un array con el ADN
     * y respondiendo true en caso de ser mutante y false en caso de no serlo
     * @param dna Es un array con las bases nitrogenadas del adn
     * @return Devuelve true o false para saber si es mutante o no respectivamente
     */
    private boolean isMutant(ArrayList<String> dna){
        int totalDnaGroup=0;
        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                if (j<3 && validate(dna.get(i).charAt(j), dna.get(i).charAt(j+1),
                        dna.get(i).charAt(j+2), dna.get(i).charAt(j+3)) ) totalDnaGroup++;
                if (i<3 && validate(dna.get(i).charAt(j), dna.get(i+1).charAt(j),
                        dna.get(i+2).charAt(j), dna.get(i+3).charAt(j)) ) totalDnaGroup++;
                if (i<3 && j<3 && validate(dna.get(i).charAt(j), dna.get(i+1).charAt(j+1),
                        dna.get(i+2).charAt(j+2), dna.get(i+3).charAt(j+3)) ) totalDnaGroup++;
                if (i<3 && j<3 && validate(dna.get(i+3).charAt(j), dna.get(i+2).charAt(j+1),
                        dna.get(i+1).charAt(j+2), dna.get(i).charAt(j+3)) ) totalDnaGroup++;
            }
        }
        return totalDnaGroup > 1;
    }

    /**
     * Saca estadisticas de las personas y mutantes analizados.
     * - Cantidad de mutantes
     * - Cantidad de humanos
     * - Relacion entre mutantes y humanos
     * @return Devuelve un json con las estadisticas obtenidas
     */
    public HashMap<String, String> stats(){
        long countMutants=personRepository.getCountMutants();
        long countHumans=personRepository.getCountHumans();
        HashMap<String, String> json = new HashMap<>();
        json.put("count_mutant_dna", countMutants+"");
        json.put("count_human_dna", countHumans+"");
        json.put("ratio", ((float)countMutants/countHumans)+"");
        return json;
    }

    /**
     * Valida si 4 bases nitrogenadas son iguales
     * @return Devuelve true si las 4 bases nitrogenadas son iguales o false si hay al menos una diferente
     */
    private boolean validate(char a, char b, char c, char d){
        return a==b && b==c && c==d;
    }
}
