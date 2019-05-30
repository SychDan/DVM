package ru.mephi.kaf82.DVM.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.mephi.kaf82.DVM.model.Entry;
import ru.mephi.kaf82.DVM.model.File;
import ru.mephi.kaf82.DVM.model.Type;

import java.util.List;

public interface FileRepository extends CrudRepository<File, Long> {

    @Query("FROM File WHERE entry =:entry AND type=:type")
    List<File> findByEntryAndType(@Param("entry") Entry entry, @Param("type")Type type);

    @Query("FROM File WHERE type=:type")
    List<File> findByType(@Param("type")Type type);

    @Query("SELECT COUNT(*) FROM File WHERE hash = :hash")
    long countByHash(@Param("hash") String hash);
}
