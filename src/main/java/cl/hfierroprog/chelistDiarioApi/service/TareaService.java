package cl.hfierroprog.chelistDiarioApi.service;

import cl.hfierroprog.chelistDiarioApi.entity.Tarea;
import cl.hfierroprog.chelistDiarioApi.pojo.TareasResponse;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

public interface TareaService {

    @Transactional
    TareasResponse getTareas() throws ParseException;

    @Transactional
    Tarea cambiarEstado(Integer id);

}
