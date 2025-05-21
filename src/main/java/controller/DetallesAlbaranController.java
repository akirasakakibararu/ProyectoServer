package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import repository.AlbaranRepository;
import repository.DetallesAlbaranRepository;
import repository.ProductoRepository;
import pojos.DetallesAlbaran;
import pojos.Productos;
import service.DetallesAlbaranService;
 
@RestController
@RequestMapping("/api/detalles")
public class DetallesAlbaranController {

    @Autowired
    private DetallesAlbaranRepository detallesRepo;

    @Autowired
    private AlbaranRepository albaranesRepo;  // Inyecta el repositorio de albaranes
    @Autowired
    private ProductoRepository productosRepo;
    @PostMapping("/lote")
    public ResponseEntity<?> insertarLote(@RequestBody List<DetallesAlbaran> detalles) {
        List<DetallesAlbaran> validos = new ArrayList<>();

        for (DetallesAlbaran detalle : detalles) {
            // Verificar si existe el albarán
            if (!albaranesRepo.existsById(detalle.getAlbaran())) {
                return ResponseEntity
                    .badRequest()
                    .body("No existe el albarán con ID " + detalle.getAlbaran() + ". No se puede añadir el detalle.");
            }

            // Verificar producto
            Optional<Productos> productoOpt = productosRepo.findById(detalle.getProducto());
            if (productoOpt.isEmpty()) {
                return ResponseEntity
                    .badRequest()
                    .body("Producto con ID " + detalle.getProducto() + " no existe.");
            }

            Productos productoBD = productoOpt.get();

            // Validar precio unitario
            if (productoBD.getPrecioUnitario().compareTo(detalle.getPrecioUnitario()) != 0) {
                return ResponseEntity
                    .badRequest()
                    .body("Precio incorrecto para el producto con ID " + detalle.getProducto()
                          + ". Debe ser: " + productoBD.getPrecioUnitario());
            }

            validos.add(detalle);
        }

        detallesRepo.saveAll(validos);
        return ResponseEntity.ok("Detalles insertados correctamente");
    }
}
