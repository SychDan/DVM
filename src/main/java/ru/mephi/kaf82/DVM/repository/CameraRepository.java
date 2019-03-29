package ru.mephi.kaf82.DVM.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mephi.kaf82.DVM.model.Camera;

public interface CameraRepository extends CrudRepository<Camera, Long> {
}
