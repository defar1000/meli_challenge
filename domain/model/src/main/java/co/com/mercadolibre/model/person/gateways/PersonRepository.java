package co.com.mercadolibre.model.person.gateways;

import co.com.mercadolibre.model.person.Person;

public interface PersonRepository {
    Person savePerson(Person person);
    long getCountHumans();
    long getCountMutants();
}
