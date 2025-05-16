package service;

 
import pojos.Productos;
import repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Crear un nuevo producto
    public Productos crearProducto(Productos producto) {
        return productoRepository.save(producto);
    }

    // Obtener todos los productos
    public List<Productos> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Obtener producto por nombre
    public List<Productos> obtenerProductosPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Obtener producto por id
    public Optional<Productos> obtenerProductoPorId(int id) {
        return productoRepository.findById(id);
    }

    // Actualizar un producto
    public Productos actualizarProducto(Productos producto) {
        return productoRepository.save(producto);
    }

    // Eliminar producto
    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    // Aumentar stock
    public void aumentarStock(int id ) throws Exception {
        Productos producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Incrementar el stock actual en una unidad
        producto.setStockActual(producto.getStockActual() + 1);
        
        // Guardar el producto actualizado en la base de datos
        productoRepository.save(producto);
    }
    public void disminuirStock(int id ) throws Exception {
        Productos producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Incrementar el stock actual en una unidad
        producto.setStockActual(producto.getStockActual() -1 );
        
        // Guardar el producto actualizado en la base de datos
        productoRepository.save(producto);
    }

 // Disminuir stock con cantidad específica


 

}
