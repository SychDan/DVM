package ru.mephi.kaf82.DVM.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mephi.kaf82.DVM.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
