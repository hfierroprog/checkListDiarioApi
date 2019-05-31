package cl.hfierroprog.chelistDiarioApi.service;

import cl.hfierroprog.chelistDiarioApi.entity.Pool;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PoolService {

    @Transactional
    List<Pool> getPool();

    @Transactional
    Pool addItemToPool(Pool pool);

    @Transactional
    boolean deleteItemPool(Integer id);
}
