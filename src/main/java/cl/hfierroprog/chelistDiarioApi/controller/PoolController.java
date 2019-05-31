package cl.hfierroprog.chelistDiarioApi.controller;

import cl.hfierroprog.chelistDiarioApi.entity.Pool;
import cl.hfierroprog.chelistDiarioApi.service.PoolService;
import cl.wom.common.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PoolController {

    @Autowired
    private PoolService poolService;

    @GetMapping("/pool")
    public ResponseEntity<?> getPool() {
        return new ResponseEntity<>(poolService.getPool(), HttpStatus.OK);
    }

    @PostMapping("/pool")
    public ResponseEntity<?> addItemToPool(@RequestBody Pool pool) {
        return new ResponseEntity<>(poolService.addItemToPool(pool),HttpStatus.OK);
    }

    @DeleteMapping("/pool/{id}")
    public ResponseEntity<?> deleteItemPool(@PathVariable("id") Integer id) {
        if(id == null) {
            throw new BadRequestException("id no valido!","Reintente con un nuevo id", "400");
        } else {
            return new ResponseEntity<>(poolService.deleteItemPool(id),HttpStatus.OK);
        }
    }
}
