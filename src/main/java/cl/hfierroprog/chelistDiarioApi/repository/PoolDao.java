package cl.hfierroprog.chelistDiarioApi.repository;

import cl.hfierroprog.chelistDiarioApi.entity.Pool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoolDao extends CrudRepository<Pool, Integer> {
    @Override
    List<Pool> findAll();

    @Override
    void deleteById(Integer integer);

    Optional<Pool> findByTitulo(String titulo);
}
