package co.com.mercadolibre.model.person;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.IllegalFormatFlagsException;

@NoArgsConstructor
public class Person {
    private ArrayList<String> dna;

    public void setDna(ArrayList<String> dna){
        if(dna.size()!=6) throw new IllegalFormatFlagsException("Tamaño del ADN invalido");
        dna.forEach(e->{
            if(e.length()!=6) throw new IllegalFormatFlagsException("Tamaño del ADN invalido");
            for(int i=0;i<6;i++){
                if(!validateBaseNitrogenada(e.charAt(i)))
                    throw new IllegalFormatFlagsException("Base Nitrogenada no valida");
            }
        });
        this.dna = dna;
    }

    public ArrayList<String> getDna(){
        return dna;
    }

    private boolean validateBaseNitrogenada(char c){
        return c=='A' || c=='T' || c=='G' || c=='C';
    }
}
