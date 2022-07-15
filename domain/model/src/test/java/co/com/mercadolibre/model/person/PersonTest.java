package co.com.mercadolibre.model.person;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatFlagsException;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void cuandoHayBaseNitrogenadaErroneaSeEsperaException() {
        Person person = new Person();
        ArrayList<String> dna =  new ArrayList<>(Arrays.asList("WTGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"));
        IllegalFormatFlagsException ex = assertThrows(IllegalFormatFlagsException.class, ()->person.setDna(dna));
        assertEquals("Base Nitrogenada no valida",ex.getFlags());
    }
    @Test
    void cuandoElTamanoDelStrinEsMayorQueSeisSeEsperaException() {
        Person person = new Person();
        ArrayList<String> dna =  new ArrayList<>(Arrays.asList("ATGCGAA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"));
        IllegalFormatFlagsException ex = assertThrows(IllegalFormatFlagsException.class, ()->person.setDna(dna));
        assertEquals("Tamaño del ADN invalido",ex.getFlags());
    }
    @Test
    void cuandoElTamanoDelArrayEsMayorQueSeisSeEsperaException() {
        Person person = new Person();
        ArrayList<String> dna =  new ArrayList<>(Arrays.asList("TGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG","TCACTG"));
        IllegalFormatFlagsException ex = assertThrows(IllegalFormatFlagsException.class, ()->person.setDna(dna));
        assertEquals("Tamaño del ADN invalido",ex.getFlags());
    }
}