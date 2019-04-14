package ru.mephi.kaf82.DVM.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.mephi.kaf82.DVM.model.Terminal;

import java.util.List;

public interface TerminalRepository extends CrudRepository<Terminal, Long> {

    @Query("select distinct nameGroup from Terminal")
    List<String> findAllGroup();

    @Query("FROM Terminal WHERE nameGroup=:nameOfGroup")
    List<Terminal> findAllByGroup(@Param("nameOfGroup") String nameOfGroup);
}
