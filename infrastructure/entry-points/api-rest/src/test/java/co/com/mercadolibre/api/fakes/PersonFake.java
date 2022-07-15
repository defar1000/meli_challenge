package co.com.mercadolibre.api.fakes;

import co.com.mercadolibre.model.person.Person;

import java.util.ArrayList;
import java.util.Arrays;

public class PersonFake{
    public static Person mutant(){
        ArrayList<String> dna = new ArrayList<>(Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"));
        Person person = new Person();
        person.setDna(dna);
        return person;
    }

    public static Person human(){
        ArrayList<String> dna = new ArrayList<>(Arrays.asList("TTGCGA","CAGTGC","TTATGT","AGAAGG","GCCCTA","TCACTG"));
        Person person = new Person();
        person.setDna(dna);
        return person;
    }
}
