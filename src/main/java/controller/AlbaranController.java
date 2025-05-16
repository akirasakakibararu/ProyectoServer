package controller;

  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojos.Albaranes;
import pojos.Productos;
import service.AlbaranService;

import com.example.server.AlbaranRepository;

@RestController
@RequestMapping("/api/albaranes")
public class AlbaranController {

 @Autowired
 private AlbaranRepository albaranRepository;
 
 @Autowired
 private AlbaranService albaService;

 // Endpoint para crear nuevo albarán
 @PostMapping
 public ResponseEntity<Albaranes> createAlbaran(@RequestBody Albaranes albaran) {
     Albaranes savedAlbaran = albaranRepository.save(albaran);
     return ResponseEntity.ok(savedAlbaran);
 }

 // Endpoint para obtener albarán por ID
 @GetMapping("/{id}")
 public ResponseEntity<Albaranes> getAlbaran(@PathVariable int id) {
     return albaranRepository.findById(id)
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());
 }
 @GetMapping
 public List<Albaranes> obtenerTodosAlbaranes() {
     return albaService.obtenerTodosLosAlbaranes();
 }
}