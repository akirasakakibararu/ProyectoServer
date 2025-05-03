package controller;

  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojos.Albaranes;
import com.example.server.AlbaranRepository;

@RestController
@RequestMapping("/api/albaranes")
public class AlbaranController {

 @Autowired
 private AlbaranRepository albaranRepository;

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
}