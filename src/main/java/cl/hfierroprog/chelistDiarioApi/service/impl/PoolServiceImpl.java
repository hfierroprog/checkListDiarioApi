package cl.hfierroprog.chelistDiarioApi.service.impl;

import cl.hfierroprog.chelistDiarioApi.entity.Pool;
import cl.hfierroprog.chelistDiarioApi.entity.Registro;
import cl.hfierroprog.chelistDiarioApi.entity.Tarea;
import cl.hfierroprog.chelistDiarioApi.repository.PoolDao;
import cl.hfierroprog.chelistDiarioApi.repository.RegistroDao;
import cl.hfierroprog.chelistDiarioApi.repository.TareaDao;
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

    @Autowired
    private TareaDao tareaDao;

    @Override
    public List<Pool> getPool() {
        return (List<Pool>) poolDao.findAll();
    }

    @Override
    public Pool addItemToPool(Pool pool) {
        if(pool.getTitulo() == null || pool.getTitulo().equals("")) {
            throw new BadRequestException("Titulo enviado no valido!","Prueba ingresando un titulo valido","400");
        } else {
            Optional<Registro> registroOptional = checkListUtil.getRegistroHoy();

            if(registroOptional.isPresent()) {
                Registro registroDB = registroOptional.get();

                Tarea tareaNueva = new Tarea();
                tareaNueva.setRegistro(registroDB);
                tareaNueva.setTitulo(pool.getTitulo());
                tareaNueva.setCompletado(false);

                tareaDao.save(tareaNueva);
            }
            return poolDao.save(pool);
        }
    }

    @Override
    public boolean deleteItemPool(Integer id) {
        Optional<Pool> pool = poolDao.findById(id);
        boolean ok;

        if(pool.isPresent()) {
            Pool poolDb = pool.get();
            String tituloTarea = poolDb.getTitulo();
            poolDao.delete(pool.get());
            Optional<Registro> registro = checkListUtil.getRegistroHoy();
            if(registro.isPresent()) {
                Registro registroDB = registro.get();
                for(Tarea t: registroDB.getTareas()) {
                    if(tituloTarea.equals(t.getTitulo())) {
                        tareaDao.delete(t);
                    }
                }
            }
            ok = true;
        } else {
            throw new NotFoundException("Pool no encontrado!", "Reintenta con un id que este registrado", "404");
        }

        return ok;
    }

}
