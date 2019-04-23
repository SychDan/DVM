package ru.mephi.kaf82.DVM.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mephi.kaf82.DVM.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Long> {
}
