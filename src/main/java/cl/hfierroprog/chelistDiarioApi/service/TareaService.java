package cl.hfierroprog.chelistDiarioApi.service;

import cl.hfierroprog.chelistDiarioApi.entity.Tarea;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

public interface TareaService {

    @Transactional
    List<Tarea> getTareas() throws ParseException;

    @Transactional
    Tarea cambiarEstado(Integer id);

}
