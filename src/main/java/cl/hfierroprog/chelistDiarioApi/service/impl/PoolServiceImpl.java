package cl.hfierroprog.chelistDiarioApi.service.impl;

import cl.hfierroprog.chelistDiarioApi.entity.Pool;
import cl.hfierroprog.chelistDiarioApi.repository.PoolDao;
import cl.hfierroprog.chelistDiarioApi.repository.RegistroDao;
import cl.hfierroprog.chelistDiarioApi.service.PoolService;
import cl.hfierroprog.chelistDiarioApi.util.CheckListUtil;
import cl.wom.common.exception.BadRequestException;
import cl.wom.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("poolService")
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolDao poolDao;

    @Autowired
    private RegistroDao registroDao;

    @Autowired
    private CheckListUtil checkListUtil;

    @Override
    public List<Pool> getPool() {
        return (List<Pool>) poolDao.findAll();
    }

    @Override
    public Pool addItemToPool(Pool pool) {
        if(pool.getTitulo() == null || pool.getTitulo().equals("")) {
            throw new BadRequestException("Titulo enviado no valido!","Prueba ingresando un titulo valido","400");
        } else {
            return poolDao.save(pool);
        }
    }

    @Override
    public boolean deleteItemPool(Integer id) {
        Optional<Pool> pool = poolDao.findById(id);
        boolean ok;

        if(pool.isPresent()) {
            poolDao.delete(pool.get());
            ok = true;
        } else {
            throw new NotFoundException("Pool no encontrado!", "Reintenta con un id que este registrado", "404");
        }

        return ok;
    }

}
