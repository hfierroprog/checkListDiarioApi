package cl.hfierroprog.chelistDiarioApi.util;

import cl.hfierroprog.chelistDiarioApi.entity.Registro;
import cl.hfierroprog.chelistDiarioApi.repository.RegistroDao;
import cl.wom.common.exception.NotFoundException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class CheckListUtil {

    @Autowired
    private RegistroDao registroDao;

    /**
     * Obtener Registro de hoy base de datos
     */
    public Optional<Registro> getRegistroHoy() {
        DateTime dateTime = new DateTime();
        Date fechaActual = dateTime.toDate();
        return registroDao.findByFecha(fechaActual);
    }

    public Date getFechaActualDate() {
        DateTime dateTime = new DateTime();
        Date fechaActual = dateTime.toDate();
        return fechaActual;
    }
}
