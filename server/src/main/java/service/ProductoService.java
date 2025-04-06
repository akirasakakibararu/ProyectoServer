package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.server.ProductosRepository;

import pojos.Productos;

@Service
public class ProductoService {

    @Autowired
    private ProductosRepository proRe;
    
    public List<Productos> obtenerTodos() {
        return proRe.findAll();
    }
    public Productos obtenerPorNombre(String nombre) {
        return proRe.findByNombre(nombre);
    }
    public Productos obtenerPorIdProducto(int idProducto) {
        return proRe.findByIdProducto(idProducto);
    }
}
