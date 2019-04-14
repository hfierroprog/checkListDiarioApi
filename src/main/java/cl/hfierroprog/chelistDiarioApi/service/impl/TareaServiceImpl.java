package cl.hfierroprog.chelistDiarioApi.service.impl;

import cl.hfierroprog.chelistDiarioApi.entity.Pool;
import cl.hfierroprog.chelistDiarioApi.entity.Registro;
import cl.hfierroprog.chelistDiarioApi.entity.Tarea;
import cl.hfierroprog.chelistDiarioApi.repository.PoolDao;
import cl.hfierroprog.chelistDiarioApi.repository.RegistroDao;
import cl.hfierroprog.chelistDiarioApi.repository.TareaDao;
import cl.hfierroprog.chelistDiarioApi.service.TareaService;
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

    @Override
    public List<Tarea> getTareas() throws ParseException {
        DateTime dateTime = new DateTime();
        Date fechaActual = dateTime.toDate();

        Optional<Registro> registro = registroDao.findByFecha(fechaActual);

        if(registro.isPresent()) {
            return registro.get().getTareas();
        } else {
            Registro registroNuevo = new Registro();

            registroNuevo.setFecha(fechaActual);
            registroNuevo = registroDao.save(registroNuevo);
            List<Tarea> tareas = new ArrayList<>();

            for(Pool p : poolDao.findAll()) {
                Tarea tareaNueva = new Tarea();

                tareaNueva.setTitulo(p.getTitulo());
                tareaNueva.setCompletado(false);
                tareaNueva.setRegistro(registroNuevo);

                tareaDao.save(tareaNueva);
                tareas.add(tareaNueva);
            }
            return tareas;
        }
    }

    @Override
    public Tarea cambiarEstado(Integer id) {
        Optional<Tarea> tareaOptional = tareaDao.findById(id);
        if(tareaOptional.isPresent()) {
            Tarea tarea = tareaOptional.get();
            if(!tarea.getCompletado()) {
                tarea.setCompletado(true);
            } else {
                tarea.setCompletado(false);
            }
            return tarea;
        } else {
            return null;

        }
    }
}
