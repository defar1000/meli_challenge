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
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Person.class));
    }

    @Override
    public Person savePerson(Person person) {
        Person result = this.save(person);
        logger.info("Save person: "+person.toString());
        return result;
    }

    @Override
    public int getCountPersons() {
        return 0;
    }

    @Override
    public int getCountMutants() {
        return 0;
    }
}
