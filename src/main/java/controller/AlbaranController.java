package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pojos.Albaranes;
import pojos.Albaranes.EstadoAlbaran;
import pojos.EnviarInformeRequest;
import service.AlbaranService;
import service.EmailService;
import service.InformeService;
import jakarta.transaction.Transactional;
import repository.AlbaranRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/albaranes")
public class AlbaranController {

    @Autowired
    private AlbaranRepository albaranRepository;
    @Autowired
    private AlbaranService albaService;
    @Autowired
    private InformeService informeService;

    // 1. Crear nuevo albarán con imagen (multipart)
    @PostMapping
    public ResponseEntity<Albaranes> crearAlbaran(@RequestBody Albaranes albaran) {
        try {
            Albaranes guardado = albaranRepository.save(albaran);
            return ResponseEntity.ok(guardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping
    public List<Albaranes> obtenerTodosAlbaranes() {
        return albaService.obtenerTodosLosAlbaranes();
    }
    // 2. Obtener albarán por ID
    @GetMapping("/{id}")
    public ResponseEntity<Albaranes> getAlbaran(@PathVariable int id) {
        return albaranRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Obtener albaranes entre dos fechas (fechaAlbaran)
    @Transactional
    @GetMapping("/fecha")

    public ResponseEntity<List<Albaranes>> getAlbaranesPorRango(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) 
    {
        try {
            Timestamp inicio = Timestamp.valueOf(fechaInicio + " 00:00:00");
            Timestamp fin = Timestamp.valueOf(fechaFin   + " 23:59:59");
            return ResponseEntity.ok(albaranRepository.findByFechaAlbaranBetween(inicio, fin));
        } catch (Exception e) {
            e.printStackTrace();  
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    // 4. Generar informe resumen
    @PostMapping("/informe")
    public ResponseEntity<?> generarInforme(@RequestBody List<Integer> ids) {
        return ResponseEntity.ok(informeService.generarResumen(ids));
    }

 @PostMapping("/informe/pdf")
public ResponseEntity<ByteArrayResource> generarPDF(@RequestBody List<Integer> ids) {
    byte[] pdf = informeService.generarPDF(ids);
    ByteArrayResource resource = new ByteArrayResource(pdf);

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=albaranes.pdf");
    headers.setContentType(MediaType.APPLICATION_PDF);

    return ResponseEntity.ok()
            .headers(headers)
            .contentLength(pdf.length)
            .body(resource);
}
 @Autowired
 private EmailService emailService;
 @PostMapping("/informe/enviar")
 public ResponseEntity<String> enviarPorCorreo(@RequestBody EnviarInformeRequest request) {
     String email = request.getEmail();
     List<Integer> ids = request.getIds();

     boolean enviado = emailService.enviarInformePorEmail(email, ids);

     if (enviado) {
         return ResponseEntity.ok("Correo enviado con éxito.");
     } else {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body("Error al enviar el correo.");
     }
 }

    
    
    @PutMapping("/{id}")
    public ResponseEntity<Albaranes> actualizarAlbaran(@PathVariable int id, @RequestBody Albaranes albaranActualizado) {
        return albaranRepository.findById(id).map(albaran -> {
            // Actualizar campos permitidos
            albaran.setNif(albaranActualizado.getNif());
            albaran.setFechaAlbaran(albaranActualizado.getFechaAlbaran());
            albaran.setEstado(albaranActualizado.getEstado());
            // Si tienes más campos en Albaranes que quieras actualizar, aquí los añades

            Albaranes guardado = albaranRepository.save(albaran);
            return ResponseEntity.ok(guardado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlbaran(@PathVariable int id) {
        return albaranRepository.findById(id).map(albaran -> {
            albaranRepository.delete(albaran);
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
