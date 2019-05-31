package cl.hfierroprog.chelistDiarioApi.service.impl;

import cl.hfierroprog.chelistDiarioApi.entity.Pool;
import cl.hfierroprog.chelistDiarioApi.entity.Registro;
import cl.hfierroprog.chelistDiarioApi.entity.Tarea;
import cl.hfierroprog.chelistDiarioApi.pojo.TareasResponse;
import cl.hfierroprog.chelistDiarioApi.repository.PoolDao;
import cl.hfierroprog.chelistDiarioApi.repository.RegistroDao;
import cl.hfierroprog.chelistDiarioApi.repository.TareaDao;
import cl.hfierroprog.chelistDiarioApi.service.TareaService;
import cl.hfierroprog.chelistDiarioApi.util.CheckListUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("tareaService")
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaDao tareaDao;

    @Autowired
    private RegistroDao registroDao;

    @Autowired
    private PoolDao poolDao;

    @Autowired
    private CheckListUtil checkListUtil;

    @Override
    public TareasResponse getTareas() throws ParseException {
        DateTime dateTime = new DateTime();
        Date fechaActual = dateTime.toDate();
        TareasResponse response = new TareasResponse();
        Optional<Registro> registro = registroDao.findByFecha(fechaActual);
        if (registro.isPresent()) {
            Registro registroDb = actualizarTareasRegistro(registro.get());
            response.setFecha(parseDateToResponse(fechaActual));
            response.setTareas(registroDb.getTareas());
            return response;
        } else {
            Registro registroNuevo = new Registro();
            registroNuevo.setFecha(fechaActual);
            registroNuevo = registroDao.save(registroNuevo);
            List<Tarea> tareas = new ArrayList<>();
            for (Pool p : poolDao.findAll()) {
                Tarea tareaNueva = new Tarea();

                tareaNueva.setTitulo(p.getTitulo());
                tareaNueva.setCompletado(false);
                tareaNueva.setRegistro(registroNuevo);

                tareaDao.save(tareaNueva);
                tareas.add(tareaNueva);
            }
            response.setFecha(parseDateToResponse(fechaActual));
            response.setTareas(tareas);
            return response;
        }
    }

    @Override
    public Tarea cambiarEstado(Integer id) {
        Optional<Tarea> tareaOptional = tareaDao.findById(id);
        if (tareaOptional.isPresent()) {
            Tarea tarea = tareaOptional.get();
            if (!tarea.getCompletado()) {
                tarea.setCompletado(true);
            } else {
                tarea.setCompletado(false);
            }
            return tarea;
        } else {
            return null;

        }
    }

    /**
     * Convertir la fecha al formato dd/mm/YYYY
     */
    String parseDateToResponse(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        String fechaString = sdf.format(fecha);
        return fechaString;
    }

    /**
     * Actualizar tareas del registro
     */
    private Registro actualizarTareasRegistro(Registro registro) {
        List<Pool> poolList = poolDao.findAll();
        List<Tarea> tareas = registro.getTareas();

        for (Pool p : poolList) {
            Optional<Tarea> tareaDB = tareaDao.findByTituloAndRegistro(p.getTitulo(), registro);
            if (!tareaDB.isPresent()) {
                Tarea tareaNueva = new Tarea();
                tareaNueva.setTitulo(p.getTitulo());
                tareaNueva.setRegistro(registro);
                tareaNueva.setCompletado(false);
                tareaNueva = tareaDao.save(tareaNueva);
                tareas.add(tareaNueva);
            }
        }

        for (Tarea t : tareas) {
            Optional<Pool> poolDb = poolDao.findByTitulo(t.getTitulo());
            if (!poolDb.isPresent()) {
                tareaDao.delete(t);
                if(tareas.size() - 1 == tareas.indexOf(t)) {
                    tareas.remove(t);
                    break;
                } else {
                    tareas.remove(t);
                }
            }
        }

        return registroDao.save(registro);
    }
}
