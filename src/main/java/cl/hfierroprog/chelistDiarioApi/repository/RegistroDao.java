package cl.hfierroprog.chelistDiarioApi.repository;

import cl.hfierroprog.chelistDiarioApi.entity.Registro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface RegistroDao extends CrudRepository<Registro, Integer> {
    Optional<Registro> findByFecha(Date fecha);
}
