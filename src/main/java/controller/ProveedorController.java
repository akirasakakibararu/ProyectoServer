package controller;

//ProveedorController.java
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import repository.ProveedorRepository;
import pojos.Proveedores;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

 @Autowired
 private ProveedorRepository proveedorRepository;

 // Endpoint para crear nuevo proveedor
 @PostMapping
 public ResponseEntity<Proveedores> createProveedor(@RequestBody Proveedores proveedor) {
     Proveedores savedProveedor = proveedorRepository.save(proveedor);
     return ResponseEntity.ok(savedProveedor);
 }
}