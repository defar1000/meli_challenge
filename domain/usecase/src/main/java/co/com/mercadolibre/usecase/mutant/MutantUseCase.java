package co.com.mercadolibre.usecase.mutant;

import co.com.mercadolibre.model.person.Person;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class MutantUseCase {
    public boolean isMutant(Person person){
        return isMutant(person.getDna());
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

    private boolean validate(char a, char b, char c, char d){
        return a==b && b==c && c==d;
    }
}
