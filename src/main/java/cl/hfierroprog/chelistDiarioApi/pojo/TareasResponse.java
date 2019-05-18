package cl.hfierroprog.chelistDiarioApi.pojo;

import cl.hfierroprog.chelistDiarioApi.entity.Tarea;
import lombok.Data;

import java.util.List;

public @Data class TareasResponse {
    private String fecha;
    private List<Tarea> tareas;
}
