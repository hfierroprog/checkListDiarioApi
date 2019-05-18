package cl.hfierroprog.chelistDiarioApi.controller;

import cl.hfierroprog.chelistDiarioApi.pojo.TareasResponse;
import cl.hfierroprog.chelistDiarioApi.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@RestController
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/tarea")
    @CrossOrigin(allowedHeaders = "*")
    public TareasResponse getTareas() throws ParseException {
        return tareaService.getTareas();
    }

    @PutMapping("/tarea/{id}")
    @CrossOrigin(allowedHeaders = "*")
    public ResponseEntity<?> cambiarEstado(@PathVariable("id") Optional<Integer> id) {
        if(id.isPresent()) {
            return new ResponseEntity<>(tareaService.cambiarEstado(id.get()), HttpStatus.OK);
        } else  {
            return new ResponseEntity<>("No se ha encontrado el id solicitado", HttpStatus.BAD_REQUEST);
        }
    }

}
