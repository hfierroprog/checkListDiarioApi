package cl.hfierroprog.chelistDiarioApi.repository;

import cl.hfierroprog.chelistDiarioApi.entity.Pool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoolDao extends CrudRepository<Pool, Integer> {
}
