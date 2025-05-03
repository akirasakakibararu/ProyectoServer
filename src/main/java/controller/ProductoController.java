package controller;

 
import pojos.Productos;
import service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.server.ProductoRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Crear producto
    @PostMapping
    public ResponseEntity<Productos> crearProducto(@RequestBody Productos producto) {
        Productos productoCreado = productoService.crearProducto(producto);
        return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
    }

    // Obtener todos los productos
    @GetMapping
    public List<Productos> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

  
    @PostMapping("/search")
    public List<Productos> buscarProductosPorNombre(@RequestBody Map<String, String> body) {
        String nombre = body.get("nombre");
        return productoService.obtenerProductosPorNombre(nombre);
    }
    // Obtener producto por id
    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerProductoPorId(@PathVariable int id) {
        Optional<Productos> producto = productoService.obtenerProductoPorId(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizarProducto(@PathVariable int id, @RequestBody Productos producto) {
        producto.setIdProducto(id);
        Productos productoActualizado = productoService.actualizarProducto(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // Aumentar stock
    @PostMapping("/{id}/aumentarStock")
    public ResponseEntity<?> aumentarStock(@PathVariable int id) {
        try {
            productoService.aumentarStock(id);
            return ResponseEntity.ok("Stock aumentado en una unidad.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al aumentar el stock.");
        }
    }
    @PostMapping("/{id}/disminuirStock")
    public ResponseEntity<?> disminuirStock(@PathVariable int id) {
        try {
            productoService.disminuirStock(id);
            return ResponseEntity.ok("Stock disminuir en una unidad.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al disminuir el stock.");
        }
    }
 




}
