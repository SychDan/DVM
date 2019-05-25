package ru.mephi.kaf82.DVM.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.mephi.kaf82.DVM.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("FROM Person WHERE cardNumber=:cardNumber")
    Person findByCardNumber(@Param("cardNumber") String cardNumber);
}
