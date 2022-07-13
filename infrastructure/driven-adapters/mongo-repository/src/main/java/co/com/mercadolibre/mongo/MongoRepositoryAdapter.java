package co.com.mercadolibre.mongo;

import co.com.mercadolibre.model.person.Person;
import co.com.mercadolibre.model.person.gateways.PersonRepository;
import co.com.mercadolibre.mongo.helper.AdapterOperations;
import co.com.mercadolibre.mongo.models.PersonDB;
import org.bson.types.ObjectId;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<Person, PersonDB, ObjectId, MongoDBRepository> implements PersonRepository
{

    private final Logger logger = Logger.getLogger(MongoRepositoryAdapter.class.getName());

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Person.class));
    }

    @Override
    public Person savePerson(Person person) {
        Person result = save(person);
        logger.info("Save person: "+person.toString());
        return result;
    }

    @Override
    public long getCountHumans() {
        long result = repository.countPersons();
        logger.info("Conteo de personas:"+result);
        return result;
    }

    @Override
    public long getCountMutants() {
        long result = repository.countMutants();
        logger.info("Conteo de mutantes:"+result);
        return result;
    }
}
