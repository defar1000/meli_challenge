package co.com.mercadolibre.usecase.mutant;

import co.com.mercadolibre.model.person.Person;
import co.com.mercadolibre.model.person.gateways.PersonRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class MutantUseCase {

    private final PersonRepository personRepository;

    public Person validateMutant(Person person){
        person.setMutant(isMutant(person.getDna()));
        personRepository.savePerson(person);
        return person;
    }

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

    public String stats(){
        long countMutants=personRepository.getCountMutants();
        long countHumans=personRepository.getCountHumans();
        return String.format("'count_mutant_dna':%s, 'count_human_dna':%s: 'ratio':%s",
                countMutants, countHumans, ((float)countMutants/countHumans));
    }

    private boolean validate(char a, char b, char c, char d){
        return a==b && b==c && c==d;
    }
}
