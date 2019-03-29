package ru.mephi.kaf82.DVM.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mephi.kaf82.DVM.model.Entry;

public interface EntryRepository extends CrudRepository<Entry, Long> {
}
