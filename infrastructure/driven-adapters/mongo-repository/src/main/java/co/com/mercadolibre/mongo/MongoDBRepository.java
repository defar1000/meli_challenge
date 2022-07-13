package co.com.mercadolibre.mongo;

import co.com.mercadolibre.model.person.Person;
import co.com.mercadolibre.mongo.models.PersonDB;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MongoDBRepository extends MongoRepository<PersonDB, ObjectId> , QueryByExampleExecutor<PersonDB> {
}
