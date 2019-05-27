package ru.mephi.kaf82.DVM.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.mephi.kaf82.DVM.model.Entry;
import ru.mephi.kaf82.DVM.model.Photo;

import java.util.List;

public interface PhotoRepository extends CrudRepository<Photo, Long> {

    @Query("FROM Photo WHERE entry =:entry")
    List<Photo> findByEntry(@Param("entry") Entry entry);
}
