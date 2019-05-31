package cl.hfierroprog.chelistDiarioApi.repository;

import cl.hfierroprog.chelistDiarioApi.entity.Registro;
import cl.hfierroprog.chelistDiarioApi.entity.Tarea;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TareaDao extends CrudRepository<Tarea, Integer> {
    Optional<Tarea> findByTituloAndRegistro(String titulo, Registro registro);
}
