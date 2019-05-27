package ru.mephi.kaf82.DVM.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.mephi.kaf82.DVM.model.Entry;
import ru.mephi.kaf82.DVM.model.Media;

import java.util.List;

public interface MediaRepository extends CrudRepository<Media, Long> {

    @Query("FROM Media WHERE entry =:entry")
    List<Media> findByEntry(@Param("entry")Entry entry);
}
