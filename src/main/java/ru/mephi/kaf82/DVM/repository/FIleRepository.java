package ru.mephi.kaf82.DVM.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.mephi.kaf82.DVM.model.Entry;
import ru.mephi.kaf82.DVM.model.File;

import java.util.List;

public interface FIleRepository extends CrudRepository<File, Long> {

    @Query("FROM File WHERE entry =:entry")
    List<File> findByEntry(@Param("entry") Entry entry);
}
