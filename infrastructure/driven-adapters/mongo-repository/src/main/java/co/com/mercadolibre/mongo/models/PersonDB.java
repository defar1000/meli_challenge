package co.com.mercadolibre.mongo.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document("mutants")
public class PersonDB {
    private ArrayList<String> dna;
    private boolean mutant;
}
