package cl.hfierroprog.chelistDiarioApi.repository;

import cl.hfierroprog.chelistDiarioApi.entity.Tarea;
import org.springframework.data.repository.CrudRepository;

public interface TareaDao extends CrudRepository<Tarea, Integer> {
}
